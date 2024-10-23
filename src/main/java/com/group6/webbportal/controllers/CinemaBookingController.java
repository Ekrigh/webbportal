package com.group6.webbportal.controllers;

import com.group6.webbportal.dto.CinemaBookingRequestDTO;
import com.group6.webbportal.dto.CinemaBookingResponseDTO;
import com.group6.webbportal.entities.CinemaBooking;
import com.group6.webbportal.mappers.CinemaBookingMapper;
import com.group6.webbportal.services.CinemaBookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static com.group6.webbportal.WebbPortalApplication.logger;
@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaBookingController {

    private final CinemaBookingService bookingService;
    private final CinemaBookingMapper bookingMapper;

    public CinemaBookingController(CinemaBookingService bookingService, CinemaBookingMapper bookingMapper){
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<CinemaBookingResponseDTO> findBookingById(@PathVariable int id){
        CinemaBooking booking = bookingService.findById(id);
        return ResponseEntity.ok(bookingMapper.toDTO(booking));
    }

    @PostMapping("/bookings")
    public ResponseEntity<CinemaBookingResponseDTO> createBooking(
            @Valid @RequestBody CinemaBookingRequestDTO bookingRequestDTO, Authentication authentication) {
        CinemaBooking booking = bookingService.createCinemaBooking(bookingRequestDTO, authentication);
        logger.info("{} {} created a cinema booking [{}].",
                authentication.getName(), authentication.getAuthorities(), booking.getBookingNr());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingMapper.toDTO(booking));
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<CinemaBookingResponseDTO> updateBooking(
            @PathVariable int id, @Valid @RequestBody CinemaBookingRequestDTO bookingRequestDTO, Authentication authentication){
        CinemaBooking updatedBooking = bookingService.updateCinemaBooking(bookingRequestDTO, id, authentication);
        logger.info("{} {} updated cinema booking [{}].",
                authentication.getName(), authentication.getAuthorities(), updatedBooking.getBookingNr());
        return ResponseEntity.ok(bookingMapper.toDTO(updatedBooking));
    }
}
