package com.roomate.model;

public class Chore {
    private int choreId;          // Unique ID for each chore
    private String choreName;     // Name of the chore
    private String lastDoneBy;    // Who did it last
    private String nextPerson;    // Who should do it next

    // --- Getters & Setters ---
    public int getChoreId() {
        return choreId;
    }

    public void setChoreId(int choreId) {
        this.choreId = choreId;
    }

    public String getChoreName() {
        return choreName;
    }

    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }

    public String getLastDoneBy() {
        return lastDoneBy;
    }

    public void setLastDoneBy(String lastDoneBy) {
        this.lastDoneBy = lastDoneBy;
    }

    public String getNextPerson() {
        return nextPerson;
    }

    public void setNextPerson(String nextPerson) {
        this.nextPerson = nextPerson;
    }
}
