package com.group6.webbportal.entities;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trips trips;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "total_price_sek", nullable = false)
    private double totalPriceSEK;

    @Column(name = "total_price_PLN", nullable = false)
    private double totalPricePLN;

    public Booking() {
    }

    public Booking(Trips trips, Customer customer, LocalDate departureDate, double totalPriceSEK, double totalPricePLN) {
        this.trips = trips;
        this.customer = customer;
        this.departureDate = departureDate;
        this.totalPriceSEK = totalPriceSEK;
        this.totalPricePLN = totalPricePLN;
    }

    // Getters och setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trips getTrip() {
        return trips;
    }

    public void setTrip(Trips trips) {
        this.trips = trips;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }

    public double getTotalPricePLN() {
        return totalPricePLN;
    }

    public void setTotalPricePLN(double totalPricePLN) {
        this.totalPricePLN = totalPricePLN;
    }
}

