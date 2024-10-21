package com.group6.webbportal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Table(name = "padel_bookings")
public class PadelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "padel_time_slot_id", nullable = false)
    @JsonBackReference
    private PadelTimeSlot padelTimeSlot;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "amount_of_players", nullable = false)
    @Min(value = 1, message = "Amount of players must exceed 1")
    private int amountOfPlayers;

    @Column(name = "total_price")
    private String totalPrice;

    public PadelBooking() {
    }

    public PadelBooking(int id, PadelTimeSlot padelTimeSlot, Customer customer, int amountOfPlayers, String totalPrice) {
        this.id = id;
        this.padelTimeSlot = padelTimeSlot;
        this.customer = customer;
        this.amountOfPlayers = amountOfPlayers;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PadelTimeSlot getPadelTimeSlot() {
        return padelTimeSlot;
    }

    public void setPadelTimeSlot(PadelTimeSlot padelTimeSlot) {
        this.padelTimeSlot = padelTimeSlot;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void setAmountOfPlayers(int amountOfPlayers) {
        this.amountOfPlayers = amountOfPlayers;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
