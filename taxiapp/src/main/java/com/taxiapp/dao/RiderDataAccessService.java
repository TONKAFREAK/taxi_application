package com.taxiapp.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Rider;

@Repository("riderDB")
public class RiderDataAccessService implements RiderDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RiderDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertRider(UUID id, Rider rider) {
        final String sql = "INSERT INTO rider (id, firstName, lastName) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, rider.getFirstName(), rider.getLastName());
    }

    @Override
    public List<Rider> selectAllRiders() {
        final String sql = "SELECT id, firstName, lastName FROM rider";
        return jdbcTemplate.query(sql, (rs, i) -> {

            UUID id = UUID.fromString(rs.getString("id"));
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            return new Rider(id, firstName, lastName);
          
        });
    }   

    @SuppressWarnings("deprecation")
    @Override
    public Optional<Rider> getRiderById(UUID id) {
        final String sql = "SELECT id, firstName, lastName FROM rider WHERE id = ?";

        try {
            Rider rider = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                UUID riderId = UUID.fromString(rs.getString("id"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return new Rider(riderId, firstName, lastName);
            });
            return Optional.ofNullable(rider);

        } catch (Exception e) {
            return Optional.empty();    
        }
    }

    @Override
    public int deleteRiderById(UUID id) {
        final String sql = "DELETE FROM rider WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateRiderById(UUID id, Rider rider) {
        final String sql = "UPDATE rider SET firstName = ?, lastName = ? WHERE id = ?";
        return jdbcTemplate.update(sql, rider.getFirstName(), rider.getLastName(), id);
    }
}