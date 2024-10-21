package com.group6.webbportal.services;

import com.group6.webbportal.dto.CustomerDTO;
import com.group6.webbportal.entities.Address;
import com.group6.webbportal.entities.Authority;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.User;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group6.webbportal.dao.CustomerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private AddressService addressService;
    private AuthorityService authorityService;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository custRepository, AddressService adrService, AuthorityService authService, UserService usrService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        customerRepository = custRepository;
        addressService = adrService;
        authorityService = authService;
        userService = usrService;
        passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + " doesn't exist"));
    }

    @Override
    public Customer findByUser(User user) {
        return customerRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for user: " + user.getUsername()));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        User user = new User();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        user.setEnabled(customerDTO.isEnabled());
        userService.save(user);

        for (String auth : customerDTO.getAuthorities()) {
            Authority authority = new Authority();
            authority.setUser(user);
            authority.setAuthority(auth);
            authorityService.save(authority);
        }

        Address dtoAddress = customerDTO.getAddress();
        Address existingAddress = addressService.findByDetails(dtoAddress.getStreet(), dtoAddress.getPostalcode(), dtoAddress.getCity());

        if (existingAddress != null) {
            customerDTO.setAddress(existingAddress);
        } else {
            addressService.save(dtoAddress);
        }

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + " doesn't exist"));
        Address address = customer.getAddress();
        customerRepository.deleteById(id);

        List<Customer> customersWithSameAddress = customerRepository.findAllByAddress(address);
        if (customersWithSameAddress.isEmpty()) {
            addressService.delete(address);
        }
    }

    @Transactional
    @Override
    public Customer updateCustomer(int id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + " doesn't exist"));
        if(!customerDTO.getUsername().equals(existingCustomer.getUser().getUsername())) {
            throw new IllegalArgumentException("Username cannot be changed.");
        }
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setEmail(customerDTO.getEmail());

        Address oldAddress = existingCustomer.getAddress();
        Address newAddress = customerDTO.getAddress();

        //Authorities
        Set<Authority> oldAuthorities = new HashSet<>(existingCustomer.getUser().getAuthorities());
        Set<String> newAuthorities = new HashSet<>(customerDTO.getAuthorities());

        for (Authority oldAuth : oldAuthorities) {
            if (!newAuthorities.contains(oldAuth.getAuthority())) {
                existingCustomer.getUser().getAuthorities().remove(oldAuth);
                authorityService.delete(oldAuth);
            } else {
                newAuthorities.remove(oldAuth.getAuthority());
            }
        }

        for (String auth : newAuthorities) {
            Authority authority = new Authority();
            authority.setUser(existingCustomer.getUser());
            authority.setAuthority(auth);
            authorityService.save(authority);
        }

        //Address
        boolean isAddressChanged = (!oldAddress.getStreet().equals(newAddress.getStreet()) ||
                !oldAddress.getPostalcode().equals(newAddress.getPostalcode()) ||
                !oldAddress.getCity().equals(newAddress.getCity()));
        //update address if changed
        if (isAddressChanged) {
            Address possibleExistingAddress = addressService.findByDetails(newAddress.getStreet(), newAddress.getPostalcode(), newAddress.getCity());
            if (possibleExistingAddress != null) {
                existingCustomer.setAddress(possibleExistingAddress);
            } else {
                addressService.save(newAddress);
                existingCustomer.setAddress(newAddress);
            }

            //check if old address is orphaned
            List<Customer> customersWithSameAddress = customerRepository.findAllByAddress(oldAddress);
            if (customersWithSameAddress.isEmpty()) {
                addressService.delete(oldAddress);
            }
        }
        return customerRepository.save(existingCustomer);
    }

}