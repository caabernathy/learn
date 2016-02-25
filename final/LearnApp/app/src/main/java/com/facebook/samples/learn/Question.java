package com.facebook.samples.learn;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Model for Question object
 */
public class Question {
    private UUID id;
    private UUID topicId;
    private String question;
    private ArrayList<String> choices;
    private int answer;

    public Question() {
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return question;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTopicId() {
        return topicId;
    }

    public void setTopicId(UUID topicId) {
        this.topicId = topicId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
