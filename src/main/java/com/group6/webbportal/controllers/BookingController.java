package com.group6.webbportal.controllers;


import com.group6.webbportal.entities.Booking;
import com.group6.webbportal.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static com.group6.webbportal.WebbPortalApplication.logger;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return getAllBookings();
    }
    @GetMapping("/bookings{id}")
    public ResponseEntity<List<Booking>> listBookingsByUser(@PathVariable int id, Authentication authentication) {
        return ResponseEntity.ok(bookingService.findAllByCustomerId(id, authentication));
    }
    @PostMapping("/bookings")
    public ResponseEntity<String> createBooking(@Valid @RequestBody Booking booking, Authentication authentication) {
        Booking createBooking = bookingService.create(booking, authentication);
        logger.info("{} with role(s){}, added booking-id: {}.", authentication.getName(), authentication.getAuthorities(),booking.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
    }
    @PutMapping("/bookings/{id}")
    public ResponseEntity<String> updateBooking(@Valid
                                                @PathVariable int id,
                                                @RequestBody Booking booking, Authentication authentication) {
        Booking updateBooking = bookingService.update(id,booking, authentication);
        logger.info("{} with role(s){}, updated booking-id: {}.", authentication.getName(), authentication.getAuthorities(), id);
        return ResponseEntity.ok("Booking updated successfully.");
    }
    @DeleteMapping("/bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable int id, Authentication authentication) {
        bookingService.delete(id);
        logger.info("{} with role(s){}, deleted booking-id: {}.", authentication.getName(), authentication.getAuthorities(), id);
    }
}
