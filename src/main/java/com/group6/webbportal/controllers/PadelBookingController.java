package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.PadelTimeSlot;
import com.group6.webbportal.services.PadelBookingService;
import com.group6.webbportal.services.PadelTimeSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
public class PadelBookingController {

    private final PadelBookingService padelBookingService;
    private final PadelTimeSlotService padelTimeSlotService;

    @Autowired
    public PadelBookingController(PadelBookingService padelBookingService, PadelTimeSlotService padelTimeSlotService) {
        this.padelBookingService = padelBookingService;
        this.padelTimeSlotService = padelTimeSlotService;
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<PadelTimeSlot>> listTimeSlots(Authentication authentication) {

        boolean isAdmin = false;

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            return ResponseEntity.ok(padelTimeSlotService.findAll());
        } else {
            return ResponseEntity.ok(padelTimeSlotService.findAllUnbooked());
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<List<PadelBooking>> listBookingsByUser(@PathVariable int id, Authentication authentication) {
        return ResponseEntity.ok(padelBookingService.findAllByCustomerId(id, authentication));
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> createBooking(@Valid @RequestBody PadelBooking padelBooking, Authentication authentication) {
        PadelBooking createdBooking = padelBookingService.create(padelBooking, authentication);
        logger.info("{} with role(s){}, added booking-id: {}.", authentication.getName(), authentication.getAuthorities(), padelBooking.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<String> updateBooking(@Valid
                                                @PathVariable int id,
                                                @RequestBody PadelBooking padelBooking, Authentication authentication) {
        PadelBooking updatedBooking = padelBookingService.update(id, padelBooking, authentication);
        logger.info("{} with role(s){}, updated booking-id: {}.", authentication.getName(), authentication.getAuthorities(), id);
        return ResponseEntity.ok("Booking updated successfully.");
    }

    @DeleteMapping("/bookings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable int id, Authentication authentication) {
        padelBookingService.deleteBooking(id);
        logger.info("{} with role(s){}, deleted booking-id: {}.", authentication.getName(), authentication.getAuthorities(), id);
    }
}
