package com.task.gamesys.dao;

import com.task.gamesys.dto.TweetDto;
import com.task.gamesys.model.Tweet;
import java.util.List;

public interface TweetDao {

    List<TweetDto> getLast10();

   int saveUniqueFromList(final List<Tweet> tweetList);
}
