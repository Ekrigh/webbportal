package com.group6.webbportal.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sushi_orders")
public class SushiOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "total_price_sek")
    private Double totalPriceSEK;

    @OneToMany(mappedBy = "sushiOrder", cascade = CascadeType.ALL)
    private List<DishQuantity> dishQuantities ;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public SushiOrder() {
    }

    public SushiOrder(Double totalPriceSEK, List<DishQuantity> dishQuantities, Customer customer) {
        this.totalPriceSEK = totalPriceSEK;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<DishQuantity> getDishQuantities() {
        return dishQuantities;
    }

    public void setDishQuantities(List<DishQuantity> dishQuantities) {
        this.dishQuantities = dishQuantities;
    }
}