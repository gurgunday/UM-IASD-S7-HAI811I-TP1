package com.example.agenda;

public class Event {
    private String date; // YYYY-MM-DD
    private String description;

    public Event(String date, String description) {
        this.date = date;
        this.description = description;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
