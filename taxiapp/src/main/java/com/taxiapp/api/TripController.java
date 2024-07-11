package com.taxiapp.api;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxiapp.entity.Trip;
import com.taxiapp.logger.GlobalLogger;
import com.taxiapp.service.TripService;

import jakarta.validation.Valid;

@RequestMapping("api/v1/trip")
@RestController
public class TripController {

    private final TripService tripService;

    private static final Logger logger = GlobalLogger.getLogger(RiderController.class);

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    
    @PostMapping
    public ResponseEntity<?> addTrip(@Valid @NonNull @RequestBody Trip trip) {
        try {
            logger.debug("Adding trip: {}", trip);
            tripService.addTrip(trip);
            return new ResponseEntity<>("Trip added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding trip: {}", e.getMessage());
            return new ResponseEntity<>("Error adding trip", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable UUID id) {
        Optional<Trip> trip = tripService.getTripById(id);
        logger.debug("Fetching trip with ID: {}", id);
        if (trip.isPresent()) {
            return new ResponseEntity<>(trip.get(), HttpStatus.OK);
        } else {
            logger.warn("Trip with ID: {} not found", id);
            return new ResponseEntity<>("Trip not found",HttpStatus.NOT_FOUND);
        }
    }    
}
