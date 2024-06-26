package com.taxiapp.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxiapp.entity.Rider;
import com.taxiapp.service.RiderService;

@RequestMapping("api/v1/rider")
@RestController
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public void addRider(@RequestBody Rider rider) {
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
    public Rider getRiderById(@PathVariable UUID id) {
        return riderService.getRiderById(id).orElse(null);
    }
    
}
