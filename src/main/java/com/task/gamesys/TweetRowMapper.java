package com.task.gamesys;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TweetRowMapper implements RowMapper<Tweet> {

    @Override
    public Tweet mapRow(
        final ResultSet rs,
        final int rowNum
    ) throws SQLException {
        final Tweet tweet = new Tweet();

        tweet.setTweetId(rs.getLong("tweet_Id"));
        tweet.setCreatedAt(rs.getString("created_at"));
        tweet.setText(rs.getString("text"));

        return tweet;
    }
}
