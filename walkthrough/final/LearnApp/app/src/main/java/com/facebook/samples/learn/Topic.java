package com.facebook.samples.learn;

import java.util.UUID;

/**
 * Model for Topic object
 */
public class Topic {
    private UUID id;
    private UUID subjectId;
    private String name;

    public Topic() {
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(UUID subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
