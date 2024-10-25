package com.group6.webbportal.entities;



import jakarta.persistence.*;

@Embeddable
public class Hotels {
    private String name; // Byt till "name" för bättre klarhet
    private double pricePerNight;

    public Hotels() {
    }

    public Hotels(String name, double pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    // Getter och Setter för "name"
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter och Setter för "pricePerNight"
    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
