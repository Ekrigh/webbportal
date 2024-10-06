package com.group6.webbportal.controllers;

import com.group6.webbportal.dto.CustomerDTO;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService custService) {
        customerService = custService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteById(id);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") int id, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok("Customer updated successfully.");
    }
}
