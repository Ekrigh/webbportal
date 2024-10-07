package com.group6.webbportal.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "padelcourts")
public class PadelCourt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "padelCourt")
    @Column(name = "padel_bookings")
    private List<PadelBooking> padelBookings;

}
