package com.task.gamesys;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface TweetService {


    List<Tweet> getAll();

    /**
     * Returns alarm log by alarm id
     * <p>
     *
     * @param tweet - Alarm log id
     * @return Found alarm log
     */
    int save(final Tweet tweet);

    void saveNewTweetsToDb();

}


@Service
class TweetServiceImpl implements TweetService {

    private final String   jwtToken = "AAAAAAAAAAAAAAAAAAAAAEfqNgEAAAAAXG6X4f4KMyoYrkgvR01AoI%2Fg7AY%3DPYJP0NhYYMfwyBkLSPVZS8YmAeooKVOUzIQCeaL9i69xfvEtl4";
    private final TweetDao tweetDao;

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
            final String uri = "https://api.twitter.com/2/users/44196397/tweets?max_results=15&tweet.fields=created_at";

            HttpEntity<String>     entity         = new HttpEntity<>("parameters", headers);
            RestTemplate           restTemplate   = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

            resp = responseEntity.getBody();
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    //    @Scheduled(fixedRate = 5000)
    private List<Tweet> getDataFromJson() {

        List<Tweet> data       = null;
        String      jsonResult = getJsonFromApi();
        try {
            ObjectMapper mapper       = new ObjectMapper();
            DataFromJson dataFromJson = mapper.readValue(jsonResult, DataFromJson.class);

            data = dataFromJson.getData();
        } catch (Exception j) {
            j.printStackTrace();
        }
        return data;
    }

    public void saveNewTweetsToDb() {
        List<Tweet> tweetList = getDataFromJson();
        for (Tweet t : tweetList) {
            save(t);
        }
    }

    @Override public List<Tweet> getAll() {
        return tweetDao.getAll();
    }

    @Override public int save(Tweet tweet) {
        return tweetDao.save(tweet);
    }
}

