package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.SushiBooking;
import com.group6.webbportal.services.SushiBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
@RequestMapping("/api/v1/sushi")
public class SushiBookingController {

    private SushiBookingService sushiBookingService;

    public SushiBookingController(SushiBookingService sushiBookingServ){
        sushiBookingService = sushiBookingServ;
    }

    @PostMapping("/bookings")
    public ResponseEntity<SushiBooking> createBooking(@RequestBody SushiBooking sushiBooking, Authentication authentication) {
        SushiBooking booking = sushiBookingService.makeBooking(sushiBooking, sushiBooking.getDishQuantities());
        logger.info("'{}' with role(s){}, made a booking in room '{}'.",authentication.getName(), authentication.getAuthorities(),sushiBooking.getSushiRoom().getName());
        return ResponseEntity.ok(booking);
    }

    @PutMapping("bookings/{id}")
    public ResponseEntity<SushiBooking> updateBooking(@PathVariable int id, @RequestBody SushiBooking sushiBooking, Authentication authentication){
        if (sushiBooking == null){
            throw new RuntimeException("Booking with id " + id + " does not exist");
        }
        SushiBooking updatedBooking = sushiBookingService.updateBooking(sushiBooking, id);

        logger.info("{} with role(s){}, updated a booking with id: '{}'.", authentication.getName(), authentication.getAuthorities(),updatedBooking.getId());
        return ResponseEntity.ok(sushiBookingService.updateBooking(sushiBooking, id));
    }

    @GetMapping("bookings/{id}")
    public ResponseEntity<List<SushiBooking>> getBookingsByCustomerId(@PathVariable int id) {
        List<SushiBooking> bookings = sushiBookingService.getBookingsByCustomerId(id);

        if (bookings.isEmpty()) {
            throw new RuntimeException ("No bookings found for customer with id " + id);
        }
        return ResponseEntity.ok(bookings);
    }

}