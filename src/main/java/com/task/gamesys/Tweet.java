package com.task.gamesys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("id")
    private Long tweetId;

    @JsonProperty("text")
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

    @Override public String toString() {
        return "Tweet{" +
            "createdAt='" + createdAt + '\'' +
            ", tweetId=" + tweetId +
            ", text='" + text + '\'' +
            '}';
    }
}