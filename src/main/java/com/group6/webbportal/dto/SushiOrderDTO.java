package com.group6.webbportal.dto;

import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.SushiDish;

import java.util.List;

public class SushiOrderDTO {

    private Customer customer;
    private List<SushiDish> dishes;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SushiDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<SushiDish> dishes) {
        this.dishes = dishes;
    }
}
