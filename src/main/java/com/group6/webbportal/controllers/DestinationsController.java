package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.Destinations;
import com.group6.webbportal.services.DestinationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.group6.webbportal.WebbPortalApplication.logger;



@RestController
@RequestMapping("/api/v1/")
public class DestinationsController {

    private DestinationsService destinationsService;

    @Autowired
    public DestinationsController(DestinationsService destinationsService) {
        this.destinationsService = destinationsService;
    }

    // Lista alla destinationer
    @GetMapping("destinations")
    public List<Destinations> getAllDestinations() {
        return destinationsService.findAll();
    }

    // Skapa en ny destination
    @PostMapping("destinations")
    public ResponseEntity<String> createDestination(@Valid @RequestBody Destinations destination, Authentication authentication) {
        Destinations createdDestination = destinationsService.createDestination(destination);
        logger.info("{} with role(s) {} added destination: {}.", authentication.getName(), authentication.getAuthorities(), destination.getCity());
        return ResponseEntity.status(HttpStatus.CREATED).body("Destination created successfully.");
    }

    // Uppdatera en destination
    @PutMapping("destinations/{id}")
    public ResponseEntity<Destinations> updateDestination(@PathVariable int id, @RequestBody Destinations destination, Authentication authentication) {
        Destinations updatedDestination = destinationsService.updateDestination(destination, id);
        logger.info("{} with role(s) {} updated destination: {}.", authentication.getName(), authentication.getAuthorities(), destination.getCity());
        return ResponseEntity.ok(updatedDestination);
    }

    // Ta bort en destination
    @DeleteMapping("destinations/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id, Authentication authentication) {
        String cityName = destinationsService.findById(id).getCity();
        destinationsService.deleteById(id);
        logger.info("{} with role(s) {} deleted destination: {}.", authentication.getName(), authentication.getAuthorities(), cityName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Destination deleted successfully.");
    }
}
