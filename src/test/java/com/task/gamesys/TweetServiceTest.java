package com.task.gamesys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesysApplication.class)
public class TweetServiceTest {

        @Autowired
        private TweetService target;

        @Test
    public void saveTweetsOnRun() {
            assertEquals(10, target.getLast10Replies().size());  }
}