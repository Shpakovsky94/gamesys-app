package com.task.gamesys.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.task.gamesys.GamesysApplication;
import com.task.gamesys.model.Tweet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesysApplication.class)
class TweetDaoImplTest {

    @Autowired
    private TweetDao target;

    @Test
    @Sql({"/test-schema.sql", "/test-data.sql"})
    void whenInjectInMemoryDataSource_thenReturnLast10TweetsCount() {
        assertEquals(10, target.getLast10().size());
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data.sql"})
    void getLast10NotNull() {
        assertNotNull(target.getLast10());
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data.sql"})
    void saveEmptyListTest() {
        List<Tweet> tweetTestList = Collections.emptyList();

        int r = target.saveUniqueFromList(tweetTestList);
        assertEquals(0, r);
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data.sql"})
    void saveNullListTest() {
        List<Tweet> tweetTestList = null;

        int r = target.saveUniqueFromList(tweetTestList);
        assertEquals(0, r);
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data.sql"})
    void saveUniqueFromListTest() {
        List<Tweet> tweetTestList = new ArrayList();
        tweetTestList.add(new Tweet(12L, "date", "text"));

        int r = target.saveUniqueFromList(tweetTestList);
        assertEquals(1, r);
    }
}