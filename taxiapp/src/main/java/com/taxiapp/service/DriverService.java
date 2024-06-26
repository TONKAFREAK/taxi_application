package com.taxiapp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taxiapp.dao.DriverDao;
import com.taxiapp.entity.Driver;

@Service
public class DriverService {

    private final DriverDao driverDao;

    @Autowired
    public DriverService(@Qualifier("mango2") DriverDao driverDao){

        this.driverDao = driverDao;

    }

    public int addDriver(Driver driver){
        return driverDao.insertDriver(driver);
    }

    public List<Driver> getAllDrivers(){
        return driverDao.selecrtAllDrivers();
    }
    
    public Optional<Driver> getDriverById(UUID id){
        return driverDao.getDriverById(id);

    }
    
}
