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
    public void addDriver(@RequestBody Driver driver){

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
    public Driver getDriverById(@PathVariable UUID id){
        return driverService.getDriverById(id);
    }


    
}
