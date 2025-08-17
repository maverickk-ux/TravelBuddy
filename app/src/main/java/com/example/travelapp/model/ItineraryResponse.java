package com.example.travelapp.model;

import java.io.Serializable;
import java.util.List;

public class ItineraryResponse implements Serializable {
    private static final long serialVersionUID = 1L;  // 🔒 Safe versioning

    private String city;
    private int days;
    private List<DayPlan> itinerary;

    // Required default constructor
    public ItineraryResponse() {}

    // Getters
    public String getCity() {
        return city;
    }

    public int getDays() {
        return days;
    }

    public List<DayPlan> getItinerary() {
        return itinerary;
    }

    // Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setItinerary(List<DayPlan> itinerary) {
        this.itinerary = itinerary;
    }
}
