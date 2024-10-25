package com.group6.webbportal.dao;


import com.group6.webbportal.entities.Booking;
import com.group6.webbportal.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomer(Customer customer);

}

