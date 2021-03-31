package com.task.gamesys.service;

import com.task.gamesys.dto.TweetDto;
import com.task.gamesys.model.Tweet;
import java.util.List;

public interface TweetService {

    /**
     * Returns last 10 replies from Elon Musk Twitter
     */
    List<TweetDto> getLast10Replies();

    /**
     * Save all new unique tweest from Elon Musk
     */
    void saveUniqueFromList(List<Tweet> tweetList);

}
