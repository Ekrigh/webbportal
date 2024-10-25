package com.group6.webbportal.services;


import com.group6.webbportal.entities.Booking;
import com.group6.webbportal.entities.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingService {
    Booking findById(int id);
    List<Booking> findAllByCustomerId(int Id, Authentication authentication);
    Booking save(Booking booking);
    Booking create(Booking booking, Authentication authentication);
    Booking update(int bookingId, Booking booking, Authentication authentication);
    void delete(int bookingId);
    Booking createBooking(Booking booking, User user);
    //List<Booking> getBookingsByTripId(int tripId);
}

