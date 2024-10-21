package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.PadelCourt;
import com.group6.webbportal.services.PadelCourtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
public class PadelCourtController {

    private final PadelCourtService padelCourtService;

    @Autowired
    public PadelCourtController(PadelCourtService padelCourtService) {
        this.padelCourtService = padelCourtService;
    }

    @PostMapping("/api/v1/courts")
    public ResponseEntity<String> addCourt(@Valid @RequestBody PadelCourt padelCourt, Authentication authentication) {
        PadelCourt createdCourt = padelCourtService.create(padelCourt);
        logger.info("{} with role(s){}, added court-id: {}.", authentication.getName(), authentication.getAuthorities(), padelCourt.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Padelcourt created successfully.");
    }

    @PutMapping("/api/v1/courts/{id}")
    public ResponseEntity<String> updateCourt(@PathVariable int id, @Valid @RequestBody PadelCourt updatedCourt, Authentication authentication) {
        PadelCourt updatedCourtResponse = padelCourtService.updateById(id, updatedCourt);
        logger.info("{} with role(s){}, updated court-id: {}.", authentication.getName(), authentication.getAuthorities(), updatedCourt.getId());
        return ResponseEntity.ok("Padelcourt updated successfully.");
    }
}
