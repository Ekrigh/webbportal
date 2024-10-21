package com.group6.webbportal.services;

import com.group6.webbportal.dto.CustomerDTO;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.User;

import java.util.List;

public interface CustomerService {
    Customer findById(int id);

    Customer findByUser(User user);

    List<Customer> findAll();

    Customer createCustomer(CustomerDTO customerDTO);

    void deleteById(int id);

    Customer updateCustomer(int id, CustomerDTO customerDTO);
}
