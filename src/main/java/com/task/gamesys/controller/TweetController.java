package com.task.gamesys.controller;

import com.task.gamesys.dto.TweetDto;
import com.task.gamesys.service.TweetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/get")
    public List<TweetDto> getLast10() {
        return tweetService.getLast10Replies();
    }

}
