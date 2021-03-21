package com.task.gamesys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface TweetService {

  /**
   * Returns alarm log by alarm id
   *
   * <p>
   *
   * @return Found alarm log
   */
  List<Tweet> getLast10();
}

@EnableScheduling
@Service
class TweetServiceImpl implements TweetService {

  private static final Logger log = LoggerFactory.getLogger(TweetServiceImpl.class);
  private final String jwtToken =
          "AAAAAAAAAAAAAAAAAAAAAEfqNgEAAAAAXG6X4f4KMyoYrkgvR01AoI%2Fg7AY%3DPYJP0NhYYMfwyBkLSPVZS8YmAeooKVOUzIQCeaL9i69xfvEtl4";
  private final TweetDao tweetDao;
  private boolean isSaveTweetsOnRunFinished = false;

  @Autowired
  public TweetServiceImpl(TweetDao tweetDao) {
    this.tweetDao = tweetDao;
  }

  private String getJsonFromApi() {
    String resp = "";
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Authorization", "Bearer " + jwtToken);
      final String uri =
              "https://api.twitter.com/2/users/44196397/tweets?max_results=15&tweet.fields=created_at";

      HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
              restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

      resp = responseEntity.getBody();
      return resp;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return resp;
  }

  private List<Tweet> getDataFromJson() throws Exception {
    String jsonResult = getJsonFromApi();
    ObjectMapper mapper = new ObjectMapper();
    DataFromJson dataFromJson = mapper.readValue(jsonResult, DataFromJson.class);

    List<Tweet> data = dataFromJson.getData();

    if (!data.isEmpty()) {
      return data;
    } else {
      throw new Exception();
    }
  }

  @EventListener(ApplicationReadyEvent.class)
  public void saveTweetsOnRun() {
    try {
      log.info("method saveTweetsOnRun starts");
      List<Tweet> tweetList = getDataFromJson();
      for (Tweet t : tweetList) {
        save(t);
        log.info("Saved tweet id:{}", t.getTweetId());
      }
      isSaveTweetsOnRunFinished = true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Scheduled(fixedRate = 5000)
  private void saveNewTweetsToDb() {
    try {
      if (isSaveTweetsOnRunFinished) {
        log.info("method saveNewTweetsToDb starts");
        List<Tweet> tweetListFromDb = getAll();
        List<Tweet> tweetList = getDataFromJson();

        if (!tweetListFromDb.isEmpty()) {
          for (Tweet t : tweetList) {
            if (tweetListFromDb.contains(t)) {
              save(t);
              log.info("Saved tweet id:{}", t.getTweetId());
            }
          }
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public List<Tweet> getLast10() {
    return tweetDao.getLast10();
  }

  private int save(Tweet tweet) {
    return tweetDao.save(tweet);
  }

  private List<Tweet> getAll() {
    return tweetDao.getAll();
  }
}
