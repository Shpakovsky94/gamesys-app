package com.task.gamesys;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

  private static final Logger log = LoggerFactory.getLogger(MainServiceImpl.class);

  private final TweetService tweetService;
  private final ParsingService parsingService;

  @Autowired
  public MainServiceImpl(TweetService tweetService, ParsingService parsingService) {
    this.tweetService = tweetService;
    this.parsingService = parsingService;
  }

//  @EventListener(ApplicationReadyEvent.class)
//  @Override
//  public void saveTweetsOnRun() {
//    try {
//      log.info("invoke saveTweetsOnRun()");
//      List<Tweet> tweetList = parsingService.getDataFromJson();
//      tweetService.saveAll(tweetList);
//    } catch (Exception e) {
//      log.error("",e);
//    }
//  }

  @Scheduled(fixedRateString = "${app.scheduledFixedRate}")
  private void saveNewTweetsToDb() {
    try {
      log.info("invoke saveNewTweetsToDb()");
      List<TweetDto> tweetListFromDb = tweetService.getAll();
      List<Tweet> tweetList = parsingService.getDataFromJson();

      if (tweetListFromDb.isEmpty()) {
        tweetService.saveAll(tweetList);
      } else {
        for (Tweet t : tweetList) {
          if (!tweetService.isTweetPresentInDb(t.getTweetId())) {
            tweetService.save(t);
            log.info("Saved tweet id:{}", t.getTweetId());
          }
        }
      }
    } catch (Exception e) {
      log.error("", e);
    }
  }
}
