package com.task.gamesys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface TweetService {

    String getJsonFromApi();

    String getDataFromJson();

}


@Service class TweetServiceImpl implements TweetService {

    private final String   jwtToken = "AAAAAAAAAAAAAAAAAAAAAEfqNgEAAAAAXG6X4f4KMyoYrkgvR01AoI%2Fg7AY%3DPYJP0NhYYMfwyBkLSPVZS8YmAeooKVOUzIQCeaL9i69xfvEtl4";
    private final TweetDao tweetDao;

    @Autowired
    public TweetServiceImpl(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }


    public String getJsonFromApi() {
        String resp = "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + jwtToken);
            final String uri = "https://api.twitter.com/2/users/44196397/tweets?max_results=10&tweet.fields=created_at";

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

    @Scheduled(fixedRate = 5000)
    public String getDataFromJson() {

        String data       = null;
        String jsonResult = getJsonFromApi();
        try {
            ObjectMapper mapper       = new ObjectMapper();
            DataFromJson dataFromJson = mapper.readValue(jsonResult, DataFromJson.class);

            data = dataFromJson.getData().get(0).toString();
        } catch (Exception j) {
            j.printStackTrace();
        }
        return data;
    }
}

