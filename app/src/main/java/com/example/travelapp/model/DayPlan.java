package com.example.travelapp.model;

import java.io.Serializable;
import java.util.List;

public class DayPlan implements Serializable {
    private int day;
    private List<Place> places;
    private Place restaurant;
    private Place hotel;

    // Required: No-arg constructor
    public DayPlan() {}

    // Getters
    public int getDay() {
        return day;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Place getRestaurant() {
        return restaurant;
    }

    public Place getHotel() {
        return hotel;
    }

    // Setters
    public void setDay(int day) {
        this.day = day;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public void setRestaurant(Place restaurant) {
        this.restaurant = restaurant;
    }

    public void setHotel(Place hotel) {
        this.hotel = hotel;
    }
}
