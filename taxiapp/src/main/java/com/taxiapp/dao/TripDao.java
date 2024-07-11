package com.taxiapp.dao;

import java.util.Optional;
import java.util.UUID;

import com.taxiapp.entity.Trip;

public interface TripDao {
    
    int insertTrip(UUID id, Trip trip);

    default int insertTrip(Trip trip) {
        UUID id = UUID.randomUUID();
        return insertTrip(id, trip);
    }

    Optional<Trip> getTripById(UUID id);

}
