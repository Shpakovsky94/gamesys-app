package com.task.gamesys;

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
        }*/

    @GetMapping("/get")
    public String getTweets() {
        return tweetService.getDataFromJson();
    }

}
