package com.task.gamesys;

public class Tweet {
    private String createdAt;
    private Long   tweetId;
    private String text;

    public Tweet() {
    }

    public Tweet(
        String createdAt,
        Long tweetId,
        String text
    ) {
        this.createdAt = createdAt;
        this.tweetId = tweetId;
        this.text = text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
