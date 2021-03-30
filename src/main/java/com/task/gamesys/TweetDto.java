package com.task.gamesys;


public class TweetDto {

  private Long tweetId;

  private String createdAt;

  private String text;

  public TweetDto() {
  }

  public TweetDto(Long tweetId, String createdAt, String text) {
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
}
