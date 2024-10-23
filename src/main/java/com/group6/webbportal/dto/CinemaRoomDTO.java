package com.group6.webbportal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CinemaRoomDTO {

    @NotNull(message = "Provide a name for the room")
    @Size(max = 50, message = "Room name must not exceed 50 characters")
    private String name;

    @NotNull(message = "Please specify a price for the room")
    @Min(value = 0, message = "The price must be positive")
    private Double priceSEK;

    @NotNull(message = "Please specify the capacity of the room")
    @Min(value = 10, message = "The capacity must be at least 10")
    private Integer capacity;

    public CinemaRoomDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(Double priceSEK) {
        this.priceSEK = priceSEK;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
