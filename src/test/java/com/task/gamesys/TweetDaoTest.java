package com.task.gamesys;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesysApplication.class)
public class TweetDaoTest {

    @Autowired
    private TweetDao target;

    @Test
    @Sql({"/schema.sql", "/test-data.sql"})
    public void whenInjectInMemoryDataSource_thenReturnCorrectTweetsCount() {
        assertEquals(11, target.getAll().size());
    }

    @Test
    @Sql({"/schema.sql", "/test-data.sql"})
    public void whenInjectInMemoryDataSource_thenReturnLast10TweetsCount() {
        assertEquals(10, target.getLast10().size());
    }

    @Test
    @Sql({"/schema.sql", "/test-data.sql"})
    public void whenInjectInMemoryDataSource_thenSaveTweet() {
        int result = target.save(new Tweet(12L, "date", "text"));
        assertEquals(1, result);
    }
}