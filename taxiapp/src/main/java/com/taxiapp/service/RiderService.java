package com.taxiapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.taxiapp.dao.RiderDao;
import com.taxiapp.entity.Rider;

@Service
public class RiderService {

    private final RiderDao riderDao;

    @Autowired
    public RiderService(@Qualifier("mango")RiderDao riderDao) {
        this.riderDao = riderDao;
    }

    public int addRider(Rider rider) {
        return riderDao.insertRider(rider);
    }

    public List<Rider> getAllRiders() {
        return riderDao.selectAllRiders();
    }

    public Rider getRiderById(UUID id) {
        return riderDao.getRiderById(id);
    }
    
}
