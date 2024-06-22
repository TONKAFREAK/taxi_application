package com.taxiapp.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
    
    private final UUID id;
    private final Driver driver;
    private final Rider rider;
    private final LocalDateTime starTime;
    private final LocalDateTime endTime;
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

