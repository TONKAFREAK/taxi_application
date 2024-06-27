package com.taxiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Rider;

@Repository("mango")
public class RiderDataAccessService implements RiderDao{

    private static List<Rider> DB = new ArrayList<>();

    @Override
    public int insertRider(UUID id, Rider rider) {
        DB.add(new Rider(id,    rider.getFirstName(), 
                                rider.getLastName())
        );
        
        return 1;
    }

    @Override
    public List<Rider> selectAllRiders() {
        
        return DB;
    }

    @Override
    public Optional<Rider> getRiderById(UUID id) {
       return DB.stream().filter( rider -> rider.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteRiderById(UUID id) {

        Optional<Rider> maybeRider = getRiderById(id);
        if(maybeRider.isEmpty()){
            return 0;
        }

        DB.remove(maybeRider.get());
        return 1;
    }

    @Override
    public int updateRiderById(UUID id, Rider rider) {
        return getRiderById(id)
                .map( r -> {
                    int indexOfRiderToUpdate = DB.indexOf(r);
                    if ( indexOfRiderToUpdate >= 0){
                        DB.set(indexOfRiderToUpdate, new Rider (id, rider.getFirstName(), rider.getLastName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
    
}
