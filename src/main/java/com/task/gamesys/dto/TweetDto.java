package com.task.gamesys.dto;


public class TweetDto {
    private String createdAt;

    private String text;

    public TweetDto() {
    }

    public TweetDto(
        String createdAt,
        String text
    ) {
        this.createdAt = createdAt;
        this.text = text;
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
