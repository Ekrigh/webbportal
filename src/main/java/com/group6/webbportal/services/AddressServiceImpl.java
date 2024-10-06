package com.group6.webbportal.services;


import com.group6.webbportal.dao.AddressRepository;
import com.group6.webbportal.entities.Address;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addrRepository) {
        addressRepository = addrRepository;
    }

    @Transactional
    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public Address findByDetails(String street, String postalcode, String city) {
        return addressRepository.findByStreetAndPostalcodeAndCity(street, postalcode, city);
    }
}
