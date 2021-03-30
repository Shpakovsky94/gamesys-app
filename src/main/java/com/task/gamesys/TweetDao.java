package com.task.gamesys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TweetDao {

  private final DataSource dataSource;

  @Autowired
  public TweetDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Boolean isTweetPresentInDb(final Long tweetId) {
    boolean isPresent = false;

    try {
      String query = "SELECT * FROM ELONS_TWEETS WHERE tweet_Id = ?";
      PreparedStatement ps
          = dataSource.getConnection().prepareStatement(query);
      ps.setInt(1, 1);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        isPresent = true;
      }
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isPresent;
  }

  public List<TweetDto> getAll() {
    List<TweetDto> resultList = new ArrayList();

    try {
      String query = "SELECT * FROM ELONS_TWEETS ORDER BY created_at desc";
      PreparedStatement ps
          = dataSource.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweetId(rs.getLong("tweet_Id"));
        tweetDto.setCreatedAt(rs.getString("created_at"));
        tweetDto.setText(rs.getString("text"));
        resultList.add(tweetDto);
      }
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

  public List<TweetDto> getLast10() {
    List<TweetDto> resultList = new ArrayList();

    try {
      String query = "SELECT * FROM ELONS_TWEETS  ORDER BY created_at desc LIMIT 0, 10";
      PreparedStatement ps
          = dataSource.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        TweetDto tweetDto = new TweetDto();
        tweetDto.setTweetId(rs.getLong("tweet_Id"));
        tweetDto.setCreatedAt(rs.getString("created_at"));
        tweetDto.setText(rs.getString("text"));
        resultList.add(tweetDto);
      }
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

  public int save(final Tweet tweet) {
    int result = 0;
    try {
      String query
          = "INSERT INTO ELONS_TWEETS (tweet_Id, created_at, text) VALUES (?, ?, ?)";
      PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
      ps.setLong(1, tweet.getTweetId());
      ps.setString(2, tweet.getCreatedAt());
      ps.setString(3, tweet.getText());
      result = ps.executeUpdate();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;

  }

  public void saveAll(final List<Tweet> tweetList) {

    try {
      String query
          = "INSERT INTO ELONS_TWEETS (tweet_Id, created_at, text) VALUES (?, ?, ?)";
      PreparedStatement ps = dataSource.getConnection().prepareStatement(query);

      for (Tweet tweet : tweetList) {
        ps.setLong(1, tweet.getTweetId());
        ps.setString(2, tweet.getCreatedAt());
        ps.setString(3, tweet.getText());
        ps.addBatch();
      }
      ps.executeBatch();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
