package com.task.gamesys;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
    List<Tweet> findTweetsFromUrl();

    String getJsonFromApi();

    Tweet getTweetFromJson();
}


@Service class TweetServiceImpl implements TweetService {

    private final String   jwtToken = "AAAAAAAAAAAAAAAAAAAAAEfqNgEAAAAAXG6X4f4KMyoYrkgvR01AoI%2Fg7AY%3DPYJP0NhYYMfwyBkLSPVZS8YmAeooKVOUzIQCeaL9i69xfvEtl4";
    private final TweetDao tweetDao;

    @Autowired
    public TweetServiceImpl(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Override
    public List<Tweet> findTweetsFromUrl() {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Tweet>        tweetList    = null;
        try {
//            Tweet[] langs = objectMapper.readValue(getJsonFromApi(), Tweet[].class);
            tweetList = objectMapper.readValue(getJsonFromApi(), new TypeReference<List<Tweet>>() {
            });
            return tweetList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweetList;
    }

    public String getJsonFromApi() {
        String resp = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + jwtToken);
            final String uri = "https://api.twitter.com/2/users/44196397/tweets?max_results=10&tweet.fields=created_at";

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

            resp = responseEntity.getBody();
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public Tweet getTweetFromJson() {

        Tweet  tweet      = null;
        String jsonResult = getJsonFromApi();
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();

            module.addDeserializer(Tweet.class, new TweetListDeserialization());
            mapper.registerModule(module);

            tweet = mapper.readValue(jsonResult, Tweet.class);

        } catch (Exception j) {
            j.printStackTrace();
        }
        return tweet;
    }

}

