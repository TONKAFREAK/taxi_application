package com.taxiapp.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver {

    private final UUID id;
    private final String firstName;
    private final String lastName;

    public Driver(@JsonProperty("id") UUID id,@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
}
