package com.task.gamesys.service;

import com.task.gamesys.model.Tweet;
import java.util.List;

public interface ParsingService {
    /**
     * Parse Json into List of Tweets
     */
    List<Tweet> getDataFromJson();

    /**
     * Get Json as String from Third party API
     */
    String getJsonFromApi();
}
