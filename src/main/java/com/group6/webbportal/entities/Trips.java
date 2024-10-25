package com.group6.webbportal.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "pricePerWeek")
    private double pricePerWeek;

    @ElementCollection
    @CollectionTable(name = "trip_hotels", joinColumns = @JoinColumn(name = "trip_id"))
    private List<Hotels> hotels; // Lista över hotell associerade med en resa

    public Trips() {
    }

    public Trips(String hotelName, String city, String country, double pricePerWeek, List<Hotels> hotels) {
        this.hotelName = hotelName;
        this.city = city;
        this.country = country;
        this.pricePerWeek = pricePerWeek;
        this.hotels = hotels;
    }

    // Getters och Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public double getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(double pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public List<Hotels> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotels> hotels) {
        this.hotels = hotels;
    }
}