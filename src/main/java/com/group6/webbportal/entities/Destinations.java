package com.group6.webbportal.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id", nullable = false)
    private int destinationId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "flight_cost", nullable = false)
    private double flightCost;

    @Column(name = "weather", nullable = false)
    private String weather;

    @ElementCollection // Kopplar listan över hotell till denna entitet
    @CollectionTable(name = "destination_hotels", joinColumns = @JoinColumn(name = "destination_id"))
    private List<Hotels> hotels;

    public Destinations() {
    }

    public Destinations(String city, String country, double flightCost, String weather, List<Hotels> hotels) {
        this.city = city;
        this.country = country;
        this.flightCost = flightCost;
        this.weather = weather;
        this.hotels = hotels;
    }

    // Getters och Setters

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(double flightCost) {
        this.flightCost = flightCost;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public List<Hotels> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotels> hotels) {
        this.hotels = hotels;
    }
}

