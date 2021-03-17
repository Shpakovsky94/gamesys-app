package com.task.gamesys;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TweetServiceImpl implements TweetService {

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
        try {
            final String uri          = "https://api.twitter.com/2/users/44196397/tweets?max_results=10&tweet.fields=created_at";
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForObject(uri, String.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
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
