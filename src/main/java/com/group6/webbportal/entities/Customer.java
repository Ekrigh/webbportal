package com.group6.webbportal.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "first_name", length=45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length=45, nullable = false)
    private String lastName;

    @Column(name = "email", length=45, nullable = false)
    private String email;

    @Valid
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Customer() {
    }

    public Customer(int id, User user, String firstName, String lastName, String email, Address address) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
