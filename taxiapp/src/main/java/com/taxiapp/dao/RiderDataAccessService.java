package com.taxiapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Rider;

@Repository("mango")
public class RiderDataAccessService implements RiderDao{

    private static List<Rider> DB = new ArrayList<>();

    @Override
    public int insertRider(UUID id, Rider rider) {
        DB.add(new Rider(id, rider.getFirstName(), rider.getLastName()));
        return 1;
    }
    
}
