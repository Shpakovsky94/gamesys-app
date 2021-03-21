package com.task.gamesys;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface TweetService {

  /**
   * Returns last 10 replies from Elon Musk Twitter
   */
  List<Tweet> getLast10Replies();
}

@EnableScheduling
@Service
class TweetServiceImpl implements TweetService {

  private static final Logger log = LoggerFactory.getLogger(TweetServiceImpl.class);
  private final TweetDao tweetDao;
  private boolean isSaveTweetsOnRunFinished = false;

  @Autowired
  public TweetServiceImpl(TweetDao tweetDao) {
    this.tweetDao = tweetDao;
  }

  @Override
  public List<Tweet> getLast10Replies() {
    return tweetDao.getLast10();
  }

  private int save(Tweet tweet) {
    return tweetDao.save(tweet);
  }

  private List<Tweet> getAll() {
    return tweetDao.getAll();
  }

  @EventListener(ApplicationReadyEvent.class)
  public void saveTweetsOnRun() {
    try {
      log.info("invoke saveTweetsOnRun()");
      List<Tweet> tweetList = getDataFromJson();
      for (Tweet t : tweetList) {
        save(t);
        log.info("Saved tweet id:{}", t.getTweetId());
      }
      isSaveTweetsOnRunFinished = true;
    } catch (Exception e) {
      log.error("error happened in saveTweetsOnRun() ", e);
    }
  }

  @Scheduled(fixedRate = 2000)
  private void saveNewTweetsToDb() {
    try {
      if (isSaveTweetsOnRunFinished) {
        log.info("invoke saveNewTweetsToDb()");
        List<Tweet> tweetListFromDb = getAll();
        List<Tweet> tweetList = getDataFromJson();

        if (!tweetListFromDb.isEmpty() && tweetList != null) {
          for (Tweet t : tweetList) {
            if (tweetListFromDb.contains(t)) {
              save(t);
              log.info("Saved tweet id:{}", t.getTweetId());
            }
          }
        }
      }
    } catch (Exception e) {
      log.error("error happened in saveNewTweetsToDb() ", e);
    }
  }

  private List<Tweet> getDataFromJson() {
    List<Tweet> data = null;
    try {
      String       jsonResult   = getJsonFromApi();
      ObjectMapper mapper       = new ObjectMapper();
      DataFromJson dataFromJson = mapper.readValue(jsonResult, DataFromJson.class);

      data = dataFromJson.getData();
    }catch (Exception e){
      log.error("error happened in getDataFromJson() ", e);
    }
    return data;
  }

  private String getJsonFromApi() {
    String resp = "";
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String headerName = "Authorization";
      String header = "Bearer ";
      String jwtToken = "AAAAAAAAAAAAAAAAAAAAAEfqNgEAAAAAXG6X4f4KMyoYrkgvR01AoI%2Fg7AY%3DPYJP0NhYYMfwyBkLSPVZS8YmAeooKVOUzIQCeaL9i69xfvEtl4";
      headers.add(headerName, header + jwtToken);
      final String uri =
              "https://api.twitter.com/2/users/44196397/tweets?max_results=15&tweet.fields=created_at";

      HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> responseEntity =
              restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

      resp = responseEntity.getBody();
      return resp;
    } catch (Exception e) {
      log.error("error happened in getJsonFromApi() ", e);
    }
    return resp;
  }
}
