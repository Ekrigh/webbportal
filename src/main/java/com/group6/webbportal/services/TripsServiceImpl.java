package com.group6.webbportal.services;


import com.group6.webbportal.dao.TripsRepository;
import com.group6.webbportal.entities.Trips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsServiceImpl implements TripsService {

    private final TripsRepository tripsRepository;

    @Autowired
    public TripsServiceImpl(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    @Override
    public List<Trips> findAll() {
        return tripsRepository.findAll();
    }


    @Override
    public Trips createTrip(Trips trips) {

        if (trips.getHotelName() == null || trips.getCity() == null || trips.getCountry() == null) {
            throw new IllegalArgumentException("Missing required fields: hotelName, city, or country");
        }

        Trips savedTrip = tripsRepository.save(trips);

        // Returnera den sparade resan
        return savedTrip;
    }

    @Override
    public Trips findById(int id) {
        return tripsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
    }

    @Override
    public Trips save(Trips trip) {
        return tripsRepository.save(trip);
    }

    @Override
    public Trips update(int id, Trips updatedTrip) {
        Trips existingTrip = findById(id);
        existingTrip.setHotelName(updatedTrip.getHotelName());
        existingTrip.setCity(updatedTrip.getCity());
        existingTrip.setCountry(updatedTrip.getCountry());
        existingTrip.setPricePerWeek(updatedTrip.getPricePerWeek());
        existingTrip.setHotels(updatedTrip.getHotels());
        return tripsRepository.save(existingTrip);
    }

    @Override
    public void deleteById(int id) {
        if (!tripsRepository.existsById(id)) {
            throw new RuntimeException("Trip not found with id: " + id);
        }
        tripsRepository.deleteById(id);
    }
}
