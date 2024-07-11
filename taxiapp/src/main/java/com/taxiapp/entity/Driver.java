package com.taxiapp.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Driver {

    private final UUID id;
    @NotBlank(message = "Driver\'s first name cannot be blank")
    private final String firstName;
    @NotBlank(message = "Driver\'s last name cannot be blank")
    private final String lastName;

    @NotBlank(message = "Car model cannot be blank")
    private final String carModel;
    @NotBlank(message = "Car make cannot be blank")
    private final String carMake;
    @NotBlank(message = "Car color cannot be blank")
    private final String carColor;
    @NotBlank(message = "Car plate cannot be blank")    
    private final String carPlate;
    @NotNull(message = "Car year cannot be blank")
    private final int carYear;

    public Driver(@JsonProperty("id") UUID id,
                @JsonProperty("firstName") String firstName, 
                @JsonProperty("lastName") String lastName,
                @JsonProperty("carModel") String carModel,
                @JsonProperty("carMake") String carMake,
                @JsonProperty("carColor") String carColor,
                @JsonProperty("carPlate") String carPlate,
                @JsonProperty("carYear") int carYear) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carModel = carModel;
        this.carMake = carMake;
        this.carColor = carColor;
        this.carPlate = carPlate;
        this.carYear = carYear;

    }
    //driver det getters 
    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    //car det getters
    public String getCarModel(){
        return carModel;
    }

    public String getCarMake(){
        return carMake;
    }

    public String getCarColor(){
        return carColor;
    }

    public String getCarPlate(){
        return carPlate;
    }

    public int getCarYear(){
        return carYear;
    }
    
}
