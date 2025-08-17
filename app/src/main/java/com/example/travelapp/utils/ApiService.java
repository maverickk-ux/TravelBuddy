package com.example.travelapp.utils;

import com.example.travelapp.model.ItineraryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/plan/")
    Call<ItineraryResponse> getItinerary(
            @Query("city") String city,
            @Query("days") int days,
            @Query("place_types") List<String> placeTypes,
            @Query("cuisine") List<String> cuisines
    );
}
