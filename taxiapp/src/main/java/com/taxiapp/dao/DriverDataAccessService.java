package com.taxiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Driver;

@Repository("mango2")
public class DriverDataAccessService implements DriverDao {

    private static List<Driver> DB = new ArrayList<>();

    @Override
    public int insertDriver(UUID id, Driver driver) {
        DB.add(new Driver(id,   driver.getFirstName(), 
                                driver.getLastName(), 
                                driver.getCarModel(), 
                                driver.getCarMake(), 
                                driver.getCarColor(), 
                                driver.getCarPlate(), 
                                driver.getCarYear())
        );

        return 1;
    }

    @Override
    public List<Driver> selecrtAllDrivers() {
       
        return DB;
    }

    @Override
    public Driver getDriverById(UUID id) {
        for (Driver driver : DB) {
            if (driver.getId().equals(id)) {
                return driver;
            }
        }
        return null;
    }
    
}
