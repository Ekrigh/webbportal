package com.group6.webbportal.services;


import com.group6.webbportal.entities.Address;

public interface AddressService {
    Address save(Address address);

    void delete(Address address);

    Address findByDetails(String street, String postalcode, String city);
}
