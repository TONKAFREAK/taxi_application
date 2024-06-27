package com.taxiapp.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class Trip {
    
    private final UUID id;
    @NotBlank(message = "Driver cannot be blank")
    private final Driver driver;
    @NotBlank(message = "Rider cannot be blank")
    private final Rider rider;
    @NotBlank(message = "Start time cannot be blank")
    private final LocalDateTime starTime;
    @NotBlank(message = "End time cannot be blank")
    private final LocalDateTime endTime;
    @NotBlank(message = "Fare cannot be blank")
    private final double fare;

    public Trip(@JsonProperty("id") UUID id,
                @JsonProperty("driver") Driver driver,
                @JsonProperty("rider") Rider rider,
                @JsonProperty("startTime") LocalDateTime starTime,
                @JsonProperty("endTime") LocalDateTime endTime,
                @JsonProperty("fare") double fare){

        this.id = id;
        this.driver = driver;
        this.rider = rider;
        this.starTime = starTime;
        this.endTime = endTime;
        this.fare = fare ;

    }

    public UUID getId(){
        return id;
    }

    public Driver getDriver(){
        return driver;
    }

    public Rider getRider(){
        return rider;
    }

    public LocalDateTime getStartTime(){
        return starTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public double getFare(){
        return fare;
    }
    
    

}

