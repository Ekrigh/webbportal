package com.group6.webbportal.services;

import com.group6.webbportal.dto.CinemaBookingRequestDTO;
import com.group6.webbportal.entities.CinemaBooking;
import org.springframework.security.core.Authentication;

public interface CinemaBookingService {

    CinemaBooking findById(int id);

    CinemaBooking createCinemaBooking(CinemaBookingRequestDTO bookingRequestDTO, Authentication authentication);

    CinemaBooking updateCinemaBooking(CinemaBookingRequestDTO bookingRequestDTO, int bookingId, Authentication authentication);
}
