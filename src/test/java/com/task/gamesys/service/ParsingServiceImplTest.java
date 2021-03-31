package com.task.gamesys.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.task.gamesys.GamesysApplication;
import com.task.gamesys.model.Tweet;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesysApplication.class)
class ParsingServiceImplTest {

    @Autowired
    private ParsingService target;

    @Test
    void getDataFromJsonNotEmptyTest() {
        List<Tweet> tweetList = target.getDataFromJson();
        assertTrue(tweetList.size() > 0);
    }

    @Test
    void getJsonFromApiNotEmptyTest() {
        String test = target.getJsonFromApi();
        assertTrue(!test.isEmpty());

    }
}