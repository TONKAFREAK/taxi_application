package com.taxiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Optional<Driver> getDriverById(UUID id) {
        return DB.stream().filter( driver -> driver.getId().equals(id)).findFirst();
    }
    
    @Override
    public int deleteDriverById(UUID id) {
        Optional<Driver> maybeDriver = getDriverById(id);
        if(maybeDriver.isEmpty()){
            return 0;
        }

        DB.remove(maybeDriver.get());
        return 1;
    }

    @Override
    public int updateDriverById(UUID id, Driver driver) {
        return getDriverById(id)
                .map( d -> {
                    int indexOfDriverToUpdate = DB.indexOf(d);
                    if ( indexOfDriverToUpdate >= 0){
                        DB.set(indexOfDriverToUpdate, new Driver (id, driver.getFirstName(), 
                                                                    driver.getLastName(), 
                                                                    driver.getCarModel(), 
                                                                    driver.getCarMake(), 
                                                                    driver.getCarColor(), 
                                                                    driver.getCarPlate(), 
                                                                    driver.getCarYear()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
