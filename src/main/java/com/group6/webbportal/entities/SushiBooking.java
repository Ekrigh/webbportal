package com.group6.webbportal.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sushi_bookings")
public class SushiBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "guest_count")
    private int guestCount;
    @Column(name = "total_price_sek")
    private Double totalPriceSEK;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private SushiRoom sushiRoom;
    @OneToMany(mappedBy = "sushiBooking")
    private List<DishQuantity> dishQuantities = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(
//            name = "sushi_bookings_dishes",
//            joinColumns = @JoinColumn(name = "booking_id"),
//            inverseJoinColumns = @JoinColumn(name = "sushi_dish_id")
//    )
//    private List<SushiDish> dishes;


    public SushiBooking() {
    }

    public SushiBooking(int guestCount, Double totalPriceSEK, Customer customer, SushiRoom sushiRoom, List<DishQuantity> dishQuantities) {
        this.guestCount = guestCount;
        this.totalPriceSEK = totalPriceSEK;
        this.customer = customer;
        this.sushiRoom = sushiRoom;
        this.dishQuantities = dishQuantities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public Double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(Double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SushiRoom getSushiRoom() {
        return sushiRoom;
    }

    public void setSushiRoom(SushiRoom sushiRoom) {
        this.sushiRoom = sushiRoom;
    }

    public List<DishQuantity> getDishQuantities() {
        return dishQuantities;
    }

    public void setDishQuantities(List<DishQuantity> dishQuantities) {
        this.dishQuantities = dishQuantities;
    }
}