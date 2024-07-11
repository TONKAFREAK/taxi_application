package com.taxiapp.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class Trip {
    
    private final UUID id;
    @NotNull(message = "Driver cannot be blank")
    private final UUID driverId;
    @NotNull(message = "Rider cannot be blank")
    private final UUID riderId;
    @NotNull(message = "Start time cannot be blank")
    private final LocalDateTime starTime;
    @NotNull(message = "End time cannot be blank")
    private final LocalDateTime endTime;
    @NotNull(message = "Fare cannot be blank")
    private final double fare;

    public Trip(@JsonProperty("id") UUID id,
                @JsonProperty("driverId") UUID driverId,
                @JsonProperty("riderId") UUID riderId,
                @JsonProperty("startTime") LocalDateTime starTime,
                @JsonProperty("endTime") LocalDateTime endTime,
                @JsonProperty("fare") double fare){

        this.id = id;
        this.driverId = driverId;
        this.riderId = riderId;
        this.starTime = starTime;
        this.endTime = endTime;
        this.fare = fare ;

    }

    public UUID getId(){
        return id;
    }

    public UUID getDriverId(){
        return driverId;
    }

    public UUID getRiderId(){
        return riderId;
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

