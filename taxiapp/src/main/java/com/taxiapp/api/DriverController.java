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

import com.taxiapp.entity.Driver;
import com.taxiapp.logger.GlobalLogger;
import com.taxiapp.service.DriverService;

import jakarta.validation.Valid;

@RequestMapping("api/v1/driver")
@RestController
public class DriverController {

    private final DriverService driverService;

    private static final Logger logger = GlobalLogger.getLogger(RiderController.class);

    @Autowired
    public DriverController(DriverService driverService){

        this.driverService = driverService;

    }

    @PostMapping
    public ResponseEntity<?> addDriver(@Valid @NonNull @RequestBody Driver driver){
        try {
            logger.debug("Adding driver: {}", driver);
            driverService.addDriver(driver);
        return new ResponseEntity<>("Driver added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding driver: {}", e.getMessage());
            return new ResponseEntity<>("Error adding driver", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public List<Driver> getAllDrivers(){
        logger.debug("Fetching all drivers");
        return driverService.getAllDrivers();
    }

    @GetMapping("/count")
    public ResponseEntity<?> getDriversCount(){
        try {
            logger.debug("Getting drivers count");
            return new ResponseEntity<>("Total number of drivers: "+driverService.getAllDrivers().size(), HttpStatus.OK); 
        } catch (Exception e) {
            logger.error("Error getting drivers count: {}", e.getMessage());
            return new ResponseEntity<>("Error getting drivers count", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable UUID id){
        Optional<Driver> driver = driverService.getDriverById(id);
        logger.debug("Fetching driver with ID: {}", id);
        if (driver.isPresent()) {
            return new ResponseEntity<>(driver.get(), HttpStatus.OK);
        } else {
            logger.warn("Driver with ID: {} not found", id);
            return new ResponseEntity<>("Driver not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriverById(@PathVariable UUID id){
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            logger.debug("Deleting driver with ID: {}", id);
            driverService.deleteDriverById(id);
            return new ResponseEntity<>("Driver deleted", HttpStatus.OK);
        } else {
            logger.warn("Driver with ID: {} not found", id);
            return new ResponseEntity<>("Driver not found",HttpStatus.NOT_FOUND);
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriverById(@PathVariable UUID id, @Valid @NonNull @RequestBody Driver driver){
        Optional<Driver> driverOpt = driverService.getDriverById(id);
        if (driverOpt.isPresent()) {
            logger.debug("Updating driver with ID: {}", id);
            driverService.updateDriverById(id, driver);
            return new ResponseEntity<>("Driver updated", HttpStatus.OK);
        } else {
            logger.warn("Driver with ID: {} not found", id);
            return new ResponseEntity<>("Driver not found",HttpStatus.NOT_FOUND);
        }
    }
}
