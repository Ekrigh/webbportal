package com.group6.webbportal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "dish_quantity")
public class DishQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private SushiBooking sushiBooking;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private SushiOrder sushiOrder;

    @ManyToOne
    @JoinColumn(name = "sushi_dish_id")
    private SushiDish sushiDish;

    @Column(name = "quantity")
    private int quantity;

    public DishQuantity() {
    }

    public DishQuantity(SushiBooking sushiBooking, SushiDish sushiDish, int quantity) {
        this.sushiBooking = sushiBooking;
        this.sushiDish = sushiDish;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public SushiDish getSushiDish() {
        return sushiDish;
    }

    public void setSushiDish(SushiDish sushiDish) {
        this.sushiDish = sushiDish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SushiBooking getSushiBooking() {
        return sushiBooking;
    }

    public void setSushiBooking(SushiBooking sushiBooking) {
        this.sushiBooking = sushiBooking;
    }

    public SushiOrder getSushiOrder() {
        return sushiOrder;
    }

    public void setSushiOrder(SushiOrder sushiOrder) {
        this.sushiOrder = sushiOrder;
    }
}
