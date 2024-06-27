package com.taxiapp.api;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxiapp.entity.Trip;
import com.taxiapp.service.TripService;

@RequestMapping("api/v1/trip")
@RestController
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    
    @PostMapping
    public void addTrip(@RequestBody Trip trip) {
        tripService.addTrip(trip);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable UUID id) {
        Optional<Trip> trip = tripService.getTripById(id);
        if (trip.isPresent()) {
            return new ResponseEntity<>(trip.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Trip not found",HttpStatus.NOT_FOUND);
        }
    }
    
    
}
