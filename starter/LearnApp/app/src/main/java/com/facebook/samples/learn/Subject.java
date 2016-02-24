package com.facebook.samples.learn;

import java.util.UUID;

/**
 * Model for Subject object
 */
public class Subject {
    private UUID id;
    private String name;
    private int order;

    public Subject() {
        id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
