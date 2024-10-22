package com.group6.webbportal.services;

import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.SushiDish;
import com.group6.webbportal.entities.SushiOrder;

import java.util.List;

public interface SushiOrderService {

//    SushiOrder placeOrder (Customer customer, List<SushiDish> dishes);
    SushiOrder createOrder(SushiOrder sushiOrder);
}
