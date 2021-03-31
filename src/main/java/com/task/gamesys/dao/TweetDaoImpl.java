package com.task.gamesys.dao;

import com.task.gamesys.dto.TweetDto;
import com.task.gamesys.model.Tweet;
import com.task.gamesys.service.ScheduledService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TweetDaoImpl implements TweetDao {

  private static final Logger log = LoggerFactory.getLogger(ScheduledService.class);

  private final DataSource dataSource;
  Connection        conn = null;
  PreparedStatement ps   = null;
  ResultSet         rs   = null;

  @Autowired
  public TweetDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<TweetDto> getLast10() {
    List<TweetDto> resultList = new ArrayList();
    try {
      String query = "SELECT * FROM ELONS_TWEETS  ORDER BY created_at desc LIMIT 0, 10";
      conn = dataSource.getConnection();
      ps = conn.prepareStatement(query);
      rs = ps.executeQuery();

      while (rs.next()) {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setCreatedAt(rs.getString("created_at"));
        tweetDto.setText(rs.getString("text"));
        resultList.add(tweetDto);
      }
    } catch (Exception e) {
      log.error(" ", e);
    } finally {
      try {
        rs.close();
      } catch (Exception ignored) {
      }
      try {
        ps.close();
      } catch (Exception ignored) {
      }
      try {
        conn.close();
      } catch (Exception ignored) {
      }
    }
    return resultList;
  }

  @Override
  public int saveUniqueFromList(final List<Tweet> tweetList) {
    int result = 0;
    try {
      String query = "INSERT IGNORE INTO ELONS_TWEETS (tweet_Id, created_at, text) VALUES (?, ?, ?)";
      conn = dataSource.getConnection();
      ps = conn.prepareStatement(query);

      for (Tweet tweet : tweetList) {
        ps.setLong(1, tweet.getTweetId());
        ps.setString(2, tweet.getCreatedAt());
        ps.setString(3, tweet.getText());
        ps.addBatch();
      }
      int[] total = ps.executeBatch();
      if (total.length > 0) {
        result = 1;
      }

    } catch (Exception e) {
      log.error(" ", e);
    } finally {
      try {
        rs.close();
      } catch (Exception ignored) {
      }
      try {
        ps.close();
      } catch (Exception ignored) {
      }
      try {
        conn.close();
      } catch (Exception ignored) {
      }
    }
    return result;
  }
}
