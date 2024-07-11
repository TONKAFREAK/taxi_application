package com.taxiapp.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxiapp.entity.Rider;
import com.taxiapp.service.RiderService;
import com.taxiapp.logger.GlobalLogger;

import jakarta.validation.Valid;

@RequestMapping("api/v1/rider")
@RestController
public class RiderController {

    private final RiderService riderService;
    private static final Logger logger = GlobalLogger.getLogger(RiderController.class);

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public ResponseEntity<?> addRider(@Valid @NonNull @RequestBody Rider rider) {

        try {
            logger.debug("Adding rider: {}", rider);
            riderService.addRider(rider);   
            return new ResponseEntity<>("Rider added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding rider: {}", e.getMessage());
            return new ResponseEntity<>("Error adding rider", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public List<Rider> getAllRiders() {
        logger.debug("Fetching all riders");
        return riderService.getAllRiders();
    }

    @GetMapping("/count")
    public ResponseEntity<?> getRidersCount() {
        try {
            logger.debug("Getting riders count");
            return new ResponseEntity<>("Total number of riders: "+riderService.getAllRiders().size(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting riders count: {}", e.getMessage());
            return new ResponseEntity<>("Error getting riders count", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRiderById(@PathVariable UUID id) {
        logger.debug("Fetching rider with ID: {}", id);
        Optional<Rider> rider = riderService.getRiderById(id);
        if (rider.isPresent()) {
            return new ResponseEntity<>(rider.get(), HttpStatus.OK);
        } else {
            logger.warn("Rider with ID: {} not found", id);
            return new ResponseEntity<>("Rider not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRiderById(@PathVariable UUID id) {
        Optional<Rider> rider = riderService.getRiderById(id);
        if (rider.isPresent()) {
            logger.debug("Deleting rider with ID: {}", id);
            riderService.deleteRiderById(id);
            return new ResponseEntity<>("Rider deleted", HttpStatus.OK);
        } else {
            logger.warn("Rider with ID: {} not found", id);
            return new ResponseEntity<>("Rider not found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRiderById(@PathVariable UUID id, @Valid @NonNull @RequestBody Rider rider) {

        Optional<Rider> riderOpt = riderService.getRiderById(id);
        if (riderOpt.isPresent()) {
            logger.debug("Updating rider with ID: {}", id);
            riderService.updateRiderById(id, rider);
            return new ResponseEntity<>("Rider updated", HttpStatus.OK);
        } else {
            logger.warn("Rider with ID: {} not found", id);
            return new ResponseEntity<>("Rider not found",HttpStatus.NOT_FOUND);
        }
    }
}
