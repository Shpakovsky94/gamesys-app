package com.task.gamesys;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

/*
    @Scheduled(fixedRate = 5000)
    public void greeting() {
        System.out.println("Hello!!!");
    }
*/

    @GetMapping("/get")
    public List<Tweet> getTweets() {
        return tweetService.findTweetsFromUrl();
    }
}
