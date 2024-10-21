package com.group6.webbportal.services;

import com.group6.webbportal.entities.PadelBooking;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PadelBookingService {

    PadelBooking findById(int id);

    List<PadelBooking> findAllByCustomerId(int id, Authentication authentication);

    PadelBooking save(PadelBooking padelBooking);

    PadelBooking create(PadelBooking padelBooking, Authentication authentication);

    PadelBooking update(int bookingId, PadelBooking padelBooking, Authentication authentication);

    void deleteBooking(int bookingId);
}
