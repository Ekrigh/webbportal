package com.group6.webbportal.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "padel_bookings")
public class PadelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "amount_of_players")
    private int amountOfPlayers;

    @ManyToOne
    @JoinColumn(name = "padel_courts")
    private PadelCourt padelCourt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "price")
    private long price;





}
