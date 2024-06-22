package com.taxiapp.dao;

import java.util.UUID;

import com.taxiapp.entity.Trip;

public interface TripDao {
    
    int insertTrip(UUID id, Trip trip);

    default int insertTrip(Trip trip) {
        UUID id = UUID.randomUUID();
        return insertTrip(id, trip);
    }

    Trip getTripById(UUID id);
}
