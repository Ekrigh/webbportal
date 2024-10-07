package com.group6.webbportal.services;

import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.User;

import java.util.List;

public interface PadelBookingService {

    List<PadelBooking> findAll();

    List<PadelBooking> findAllbyUser(User user);

    List<PadelBooking> findAllByAvailability();

    PadelBooking save(PadelBooking padelBooking);

    void deleteById(int id);
}
