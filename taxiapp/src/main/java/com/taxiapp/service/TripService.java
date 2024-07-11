package com.taxiapp.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taxiapp.dao.TripDao;
import com.taxiapp.entity.Trip;

@Service
public class TripService {

    private final TripDao tripDao;

    @Autowired
    public TripService(@Qualifier("tripDB")TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public int addTrip(Trip trip) {
        return tripDao.insertTrip(trip);
    }

    public Optional<Trip> getTripById(UUID id) {
        return tripDao.getTripById(id);
    }
    
}
