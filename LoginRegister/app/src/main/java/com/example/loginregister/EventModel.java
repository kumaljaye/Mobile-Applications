package com.example.loginregister;

public class EventModel {
    private String Title;
    private String Date;
    private String Location;
    private String Description;

    public EventModel() {
        // Needed for Firebase
    }

    public EventModel(String title, String date, String location, String description) {
        this.Title = title;
        this.Date = date;
        this.Location = location;
        this.Description = description;
    }

    public String getTitle() { return Title; }
    public String getDate() { return Date; }
    public String getLocation() { return Location; }
    public String getDescription() { return Description; }
}
