package com.task.gamesys.service;

import com.task.gamesys.model.Tweet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

  private static final Logger         log = LoggerFactory.getLogger(ScheduledService.class);
  private final        TweetService   tweetService;
  private final        ParsingService parsingService;

  @Autowired
  public ScheduledService(
      TweetService tweetService,
      ParsingService parsingService
  ) {
    this.tweetService = tweetService;
    this.parsingService = parsingService;
  }

  @Scheduled(fixedRateString = "${app.scheduledFixedRate}")
  private void saveNewTweetsToDb() {
    try {
      log.info("invoke saveNewTweetsToDb()");
      List<Tweet> tweetList = parsingService.getDataFromJson();
      if (tweetList != null && !tweetList.isEmpty()) {
        tweetService.saveUniqueFromList(tweetList);
      }
    } catch (Exception e) {
      log.error("", e);
    }
  }
}
