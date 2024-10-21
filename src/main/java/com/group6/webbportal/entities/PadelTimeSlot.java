package com.group6.webbportal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "padel_timeslots")
public class PadelTimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "price_per_player", nullable = false)
    private long pricePerPlayer;

    @ManyToOne
    @JoinColumn(name = "padel_court_id", nullable = false)
    private PadelCourt padelCourt;

    @OneToOne(mappedBy = "padelTimeSlot", cascade = CascadeType.ALL, orphanRemoval = true)
    private PadelBooking padelBooking;

    public PadelTimeSlot() {
    }

    public PadelTimeSlot(int id, LocalDateTime startTime, LocalDateTime endTime, PadelCourt padelCourt, long pricePerPlayer) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.padelCourt = padelCourt;
        this.pricePerPlayer = pricePerPlayer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public PadelCourt getPadelCourt() {
        return padelCourt;
    }

    public void setPadelCourt(PadelCourt padelCourt) {
        this.padelCourt = padelCourt;
    }

    public long getPricePerPlayer() {
        return pricePerPlayer;
    }

    public void setPricePerPlayer(long pricePerPlayer) {
        this.pricePerPlayer = pricePerPlayer;
    }

    public PadelBooking getPadelBooking() {
        return padelBooking;
    }

    public void setPadelBooking(PadelBooking padelBooking) {
        this.padelBooking = padelBooking;
    }
}
