package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.Trips;
import com.group6.webbportal.services.BookingService;
import com.group6.webbportal.services.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.group6.webbportal.WebbPortalApplication.logger;


@RestController
@RequestMapping("/api/v1/")
public class TripsController {

    private TripsService tripsService;
    private BookingService bookingService;

    @Autowired
    public TripsController(TripsService tripsService, BookingService bookingService) {
        this.tripsService = tripsService;
        this.bookingService = bookingService;
    }

    // 1. Lista alla resor
    @GetMapping("trips")
    public ResponseEntity<List<Trips>> getAllTrips() {
        List<Trips> trips = tripsService.findAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // 2. Hämta en specifik resa baserad på ID
    @GetMapping("trips/{id}")
    public ResponseEntity<Trips> getTripById(@PathVariable int id) {
        Trips trip = tripsService.findById(id);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    // 3. Skapa en ny resa
    @PostMapping("trips")
    public ResponseEntity<Trips> createTrip(@RequestBody Trips trip, Authentication authentication) {
        Trips newTrip = tripsService.createTrip(trip);
        logger.info("{} with role(s) {} created a new trip: {}.", authentication.getName(), authentication.getAuthorities(), trip.getHotelName());
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    // 4. Uppdatera en befintlig resa
    @PutMapping("trips/{id}")
    public ResponseEntity<Trips> updateTrip(@PathVariable int id, @RequestBody Trips updatedTrip, Authentication authentication) {
        Trips trip = tripsService.update(id, updatedTrip);
        logger.info("{} with role(s) {} updated trip: {}.", authentication.getName(), authentication.getAuthorities(), updatedTrip.getHotelName());
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    // 5. Radera en resa
    @DeleteMapping("trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id, Authentication authentication) {
        String hotelName = tripsService.findById(id).getHotelName();
        tripsService.deleteById(id);
        logger.info("{} with role(s) {} deleted trip: {}.", authentication.getName(), authentication.getAuthorities(), hotelName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}


