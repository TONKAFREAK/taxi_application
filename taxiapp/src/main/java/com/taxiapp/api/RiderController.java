package com.taxiapp.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import jakarta.validation.Valid;

@RequestMapping("api/v1/rider")
@RestController
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public void addRider(@Valid @NonNull @RequestBody Rider rider) {
        riderService.addRider(rider);   
    }

    @GetMapping("/list")
    public List<Rider> getAllRiders() {
        return riderService.getAllRiders();
    }

    @GetMapping("/count")
    public int getRidersCount() {
        return riderService.getAllRiders().size();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRiderById(@PathVariable UUID id) {
        Optional<Rider> rider = riderService.getRiderById(id);
        if (rider.isPresent()) {
            return new ResponseEntity<>(rider.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rider not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRiderById(@PathVariable UUID id) {
        riderService.deleteRiderById(id);
    }

    @PutMapping("/{id}")
    public void updateRiderById(@PathVariable UUID id, @Valid @NonNull @RequestBody Rider rider) {
        riderService.updateRiderById(id, rider);
    }

    
}
