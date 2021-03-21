package com.task.gamesys;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TweetDao {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(final DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public List<Tweet> getLast10() {
    return jdbcTemplate.query("SELECT * FROM ELONS_TWEETS  ORDER BY created_at desc LIMIT 0, 10", new TweetRowMapper());
  }

  public List<Tweet> getAll() {
    return jdbcTemplate.query("SELECT * FROM ELONS_TWEETS ORDER BY created_at desc", new TweetRowMapper());
  }

  public int save(final Tweet tweet) {
    return jdbcTemplate.update(
            "INSERT INTO ELONS_TWEETS VALUES (?, ?, ?)",
            tweet.getTweetId(),
            tweet.getCreatedAt(),
            tweet.getText());
  }
}
