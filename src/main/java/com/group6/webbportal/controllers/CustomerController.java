package com.group6.webbportal.controllers;

import com.group6.webbportal.dto.CustomerDTO;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService custService) {
        customerService = custService;
    }

    @GetMapping("/api/v1/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, Authentication authentication) {
        customerService.createCustomer(customerDTO);
        logger.info("{} with role(s){}, added user {}.", authentication.getName(), authentication.getAuthorities(), customerDTO.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
    }

    @DeleteMapping("/api/v1/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id, Authentication authentication) {
        String userName = customerService.findById(id).getUser().getUsername();
        customerService.deleteById(id);
        logger.info("{} with role(s){}, deleted user {}.", authentication.getName(), authentication.getAuthorities(), userName);
    }

    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") int id, @Valid @RequestBody CustomerDTO customerDTO, Authentication authentication) {
        customerService.updateCustomer(id, customerDTO);
        logger.info("{} with role(s){}, updated user {}.", authentication.getName(), authentication.getAuthorities(), customerService.findById(id).getUser().getUsername());
        return ResponseEntity.ok("Customer updated successfully.");
    }
}
