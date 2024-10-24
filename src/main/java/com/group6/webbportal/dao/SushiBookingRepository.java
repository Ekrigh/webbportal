package com.group6.webbportal.dao;

import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.SushiBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SushiBookingRepository extends JpaRepository <SushiBooking, Long> {
    List<SushiBooking> findAllByCustomer(Customer customer);
}