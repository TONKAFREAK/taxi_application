package com.taxiapp.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.taxiapp.entity.Driver;

public interface DriverDao {

    int insertDriver(UUID id, Driver driver);

    default int insertDriver(Driver driver) {
        UUID id = UUID.randomUUID();
        return insertDriver(id, driver);
    }
    
    List<Driver> selecrtAllDrivers();

    Optional<Driver> getDriverById(UUID id);

    int deleteDriverById(UUID id);

    int updateDriverById(UUID id, Driver driver);

}
