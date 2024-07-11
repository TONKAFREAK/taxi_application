package com.taxiapp.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.taxiapp.entity.Driver;

@Repository("driverDB")
public class DriverDataAccessService implements DriverDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DriverDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertDriver(UUID id, Driver driver) {
        final String sql = "INSERT INTO driver (id, firstName, lastName, carModel, carMake, carColor, carPlate, carYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, id, driver.getFirstName(), driver.getLastName(), driver.getCarModel(), driver.getCarMake(), driver.getCarColor(), driver.getCarPlate(), driver.getCarYear());    
    }

    @Override
    public List<Driver> selecrtAllDrivers() {
        final String sql = "SELECT id, firstName, lastName, carModel, carMake, carColor, carPlate, carYear FROM driver";
        return jdbcTemplate.query(sql, (rs, i) -> {

            UUID id = UUID.fromString(rs.getString("id"));
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String carModel = rs.getString("carModel");
            String carMake = rs.getString("carMake");
            String carColor = rs.getString("carColor");
            String carPlate = rs.getString("carPlate");
            int carYear = rs.getInt("carYear");
            return new Driver(id, firstName, lastName, carModel, carMake, carColor, carPlate, carYear);
        });    
    }

    @SuppressWarnings("deprecation")
    @Override
    public Optional<Driver> getDriverById(UUID id) {
        final String sql = "SELECT id, firstName, lastName, carModel, carMake, carColor, carPlate, carYear FROM driver WHERE id = ?";
        
        try {
            Driver driver = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
                UUID driverId = UUID.fromString(rs.getString("id"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String carModel = rs.getString("carModel");
                String carMake = rs.getString("carMake");
                String carColor = rs.getString("carColor");
                String carPlate = rs.getString("carPlate");
                int carYear = rs.getInt("carYear");
                return new Driver(driverId, firstName, lastName, carModel, carMake, carColor, carPlate, carYear);
            });
            return Optional.ofNullable(driver);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteDriverById(UUID id) {
        final String sql = "DELETE FROM driver WHERE id = ?";
        return jdbcTemplate.update(sql, id);    
    }

    @Override
    public int updateDriverById(UUID id, Driver driver) {
        final String sql = "UPDATE driver SET firstName = ?, lastName = ?, carModel = ?, carMake = ?, carColor = ?, carPlate = ?, carYear = ? WHERE id = ?";
        return jdbcTemplate.update(sql, driver.getFirstName(), driver.getLastName(), driver.getCarModel(), driver.getCarMake(), driver.getCarColor(), driver.getCarPlate(), driver.getCarYear(), id);
    }
}
 