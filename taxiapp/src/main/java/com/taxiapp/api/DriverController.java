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

import com.taxiapp.entity.Driver;
import com.taxiapp.service.DriverService;

@RequestMapping("api/v1/driver")
@RestController
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){

        this.driverService = driverService;

    }

    @PostMapping
    public void addDriver(@NonNull @RequestBody Driver driver){

        driverService.addDriver(driver);

    }

    @GetMapping("/list")
    public List<Driver> getAllDrivers(){

        return driverService.getAllDrivers();
    }

    @GetMapping("/count")
    public int getDriversCount(){
        return driverService.getAllDrivers().size();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable UUID id){
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            return new ResponseEntity<>(driver.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Driver not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDriverById(@PathVariable UUID id){
        driverService.deleteDriverById(id);
    }

    @PutMapping("/{id}")
    public void updateDriverById(@PathVariable UUID id, @NonNull @RequestBody Driver driver){
        driverService.updateDriverById(id, driver);
    }

}
