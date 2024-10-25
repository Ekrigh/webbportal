package com.group6.webbportal.services;
import com.group6.webbportal.dao.DestinationsRepository;
import com.group6.webbportal.entities.Destinations;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationsServiceImpl implements DestinationsService {

    private final DestinationsRepository destinationsRepository;

    @Autowired
    public DestinationsServiceImpl(DestinationsRepository destinationsRepository) {
        this.destinationsRepository = destinationsRepository;
    }

    @Override
    public Destinations findById(int id) {
        return destinationsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Destination with id: " + id + " doesn't exist"));
    }

    @Override
    public List<Destinations> findAll() {
        return destinationsRepository.findAll();
    }

    @Override
    public Destinations save(Destinations destination) {
        return destinationsRepository.save(destination);
    }

    @Override
    public Destinations updateDestination(Destinations destination, int id) {
        Optional<Destinations> optionalDestination = destinationsRepository.findById(id);
        if (optionalDestination.isPresent()) {
            Destinations existingDestination = optionalDestination.get();
            existingDestination.setCity(destination.getCity());
            existingDestination.setCountry(destination.getCountry());
            existingDestination.setFlightCost(destination.getFlightCost());
            existingDestination.setWeather(destination.getWeather());
            existingDestination.setHotels(destination.getHotels());
            return destinationsRepository.save(existingDestination);
        } else {
            throw new RuntimeException("Destination not found with id: " + id);
        }
    }

    @Override
    public Destinations createDestination(Destinations destinations) {
        // Validera att nödvändiga fält är ifyllda
        if (destinations.getCity() == null || destinations.getCountry() == null) {
            throw new IllegalArgumentException("City and country are required fields.");
        }

        // Spara destinationen i databasen
        Destinations savedDestination = destinationsRepository.save(destinations);

        // Returnera den sparade destinationen
        return savedDestination;
    }

    @Override
    public ResponseEntity<String> deleteById(int id) {
        if (destinationsRepository.existsById(id)) {
            destinationsRepository.deleteById(id);
            return new ResponseEntity<>("Destination deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Destination not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
