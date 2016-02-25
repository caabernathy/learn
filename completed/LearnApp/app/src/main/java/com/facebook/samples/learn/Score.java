package com.facebook.samples.learn;

import java.util.Date;
import java.util.UUID;

/**
 * Model for Score object
 */
public class Score {
    private UUID id;
    private Date createdAt;
    private String topicName;
    private int score;

    public Score() {
        id = UUID.randomUUID();
        createdAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
