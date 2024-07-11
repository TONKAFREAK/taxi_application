package com.taxiapp.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class Rider {

    private final UUID id;

    @NotBlank(message = "Rider\'s first name cannot be blank")
    private final String firstName;

    @NotBlank(message = "Rider\'s last name cannot be blank")
    private final String lastName;

    public Rider(@JsonProperty("id") UUID id,
                @JsonProperty("firstName") String firstName, 
                @JsonProperty("lastName") String lastName) {
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
