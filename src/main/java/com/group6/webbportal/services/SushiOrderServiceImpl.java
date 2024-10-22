package com.group6.webbportal.services;

import com.group6.webbportal.dao.CustomerRepository;
import com.group6.webbportal.dao.SushiDishRepository;
import com.group6.webbportal.dao.SushiOrderRepository;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.DishQuantity;
import com.group6.webbportal.entities.SushiDish;
import com.group6.webbportal.entities.SushiOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SushiOrderServiceImpl implements SushiOrderService{

    private SushiOrderRepository sushiOrderRepository;
    private CustomerRepository customerRepository;

    private SushiDishRepository sushiDishRepository;

    @Autowired
    public SushiOrderServiceImpl(SushiOrderRepository sushiOrderRepo, CustomerRepository customerRepo, SushiDishRepository sushiDishRepo){
        sushiOrderRepository = sushiOrderRepo;
        customerRepository = customerRepo;
        sushiDishRepository = sushiDishRepo;
    }
@Override
public SushiOrder createOrder(SushiOrder order) {
    Customer customer = customerRepository.findById(order.getCustomer().getId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    order.setCustomer(customer);

    List<DishQuantity> dishQuantities = new ArrayList<>();
    double totalPrice = 0.0;

    for (DishQuantity dishQuantity : order.getDishQuantities()) {
        SushiDish sushiDish = sushiDishRepository.findById(dishQuantity.getSushiDish().getId())
                .orElseThrow(() -> new IllegalArgumentException("Dish with ID " + dishQuantity.getSushiDish().getId() + " does not exist"));

        Double price = sushiDish.getPriceSEK();
        if (price == null) {
            throw new IllegalArgumentException("Dish price cannot be null for dish: " + sushiDish.getId());
        }

        DishQuantity newDishQuantity = new DishQuantity();
        newDishQuantity.setSushiDish(sushiDish);
        newDishQuantity.setQuantity(dishQuantity.getQuantity());
        newDishQuantity.setSushiOrder(order);

        dishQuantities.add(newDishQuantity);

        totalPrice += price * dishQuantity.getQuantity();
    }

    order.setTotalPriceSEK(totalPrice);
    order.setDishQuantities(dishQuantities);

    return sushiOrderRepository.save(order);
    }
}