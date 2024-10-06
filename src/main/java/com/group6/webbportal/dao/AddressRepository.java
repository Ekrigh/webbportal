package com.group6.webbportal.dao;

import com.group6.webbportal.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByStreetAndPostalcodeAndCity(String street, String postalcode, String city);
}
