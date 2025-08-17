package com.example.travelapp.model;

import java.io.Serializable;

public class Place implements Serializable {
    private String name;
    private String location;
    private double rating;

    // Required: No-arg constructor
    public Place() {}

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
