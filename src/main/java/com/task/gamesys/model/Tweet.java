package com.task.gamesys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {

  @JsonProperty("id")
  private Long tweetId;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("text")
  private String text;

  public Tweet() {
  }

  public Tweet(
      Long tweetId,
      String createdAt,
      String text
  ) {
    this.tweetId = tweetId;
    this.createdAt = createdAt;
    this.text = text;
  }

  public Long getTweetId() {
    return tweetId;
  }

  public void setTweetId(Long tweetId) {
    this.tweetId = tweetId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "tweetId=" + tweetId +
        ", createdAt='" + createdAt + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
