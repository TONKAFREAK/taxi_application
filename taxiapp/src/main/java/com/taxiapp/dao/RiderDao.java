package com.taxiapp.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.taxiapp.entity.Rider;

public interface RiderDao {

    int insertRider(UUID id, Rider rider);

    default int insertRider(Rider rider) {
        UUID id = UUID.randomUUID();
        return insertRider(id, rider);
    }

    List<Rider> selectAllRiders();

    Optional<Rider> getRiderById(UUID id);

    int deleteRiderById(UUID id);

    int updateRiderById(UUID id, Rider rider);
    
}
