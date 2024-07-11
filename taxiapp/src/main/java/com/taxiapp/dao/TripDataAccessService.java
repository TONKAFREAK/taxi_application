package com.taxiapp.dao;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Trip;

@Repository("tripDB")
public class TripDataAccessService implements TripDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TripDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTrip(UUID id, Trip trip) {
        final String sql = "INSERT INTO trip (id, driver_Id, rider_Id, startTime, endTime, fare) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, trip.getDriverId(), trip.getRiderId(), trip.getStartTime(), trip.getEndTime(), trip.getFare());    
    }

    @SuppressWarnings("deprecation")
    @Override
    public Optional<Trip> getTripById(UUID id) {
        final String sql = "SELECT id, driver_Id, rider_Id, startTime, endTime, fare FROM trip WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, (rs, i) -> {
            UUID tripId = UUID.fromString(rs.getString("id"));
            UUID driverId = UUID.fromString(rs.getString("driver_Id"));
            UUID riderId = UUID.fromString(rs.getString("rider_Id"));
            LocalDateTime startTime = rs.getTimestamp("startTime").toLocalDateTime();
            LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
            double fare = rs.getDouble("fare");
            return new Trip(tripId, driverId, riderId, startTime, endTime, fare);
        }).stream().findFirst();
    }
    
    

}
