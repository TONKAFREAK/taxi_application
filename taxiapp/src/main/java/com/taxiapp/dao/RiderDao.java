package com.taxiapp.dao;

import java.util.List;
import java.util.UUID;

import com.taxiapp.entity.Rider;

public interface RiderDao {

    int insertRider(UUID id, Rider rider);

    default int insertRider(Rider rider) {
        UUID id = UUID.randomUUID();
        return insertRider(id, rider);
    }

    List<Rider> selectAllRiders();

    Rider getRiderById(UUID id);
    
}
