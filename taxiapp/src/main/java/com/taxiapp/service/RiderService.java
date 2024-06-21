package com.taxiapp.service;

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
    
}
