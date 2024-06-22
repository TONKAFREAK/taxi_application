package com.taxiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Trip;

@Repository("mango3")
public class TripDataAccessService implements TripDao {

    private static List<Trip> DB = new ArrayList<>();

    @Override
    public int insertTrip(UUID id, Trip trip) {
        DB.add(new Trip(id, trip.getDriver(), trip.getRider(), trip.getStartTime(), trip.getEndTime(), trip.getFare()));
        return 1;
    }

    @Override
    public Trip getTripById(UUID id) {

        for (Trip trip : DB) {
            if (trip.getId().equals(id)) {
                return trip;
            }
        }
        return null;
        
    }


    
}
