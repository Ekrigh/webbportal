package com.group6.webbportal.dao;

import com.group6.webbportal.entities.Address;
import com.group6.webbportal.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByAddress(Address address);
}
