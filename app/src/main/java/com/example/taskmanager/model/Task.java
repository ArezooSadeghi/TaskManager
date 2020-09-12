package com.example.taskmanager.model;

import android.provider.ContactsContract;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private Time mTime;
    private State mState;
    private UUID mId;

    public Task() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        mState = state;
    }
}
