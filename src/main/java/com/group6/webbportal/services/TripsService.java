package com.group6.webbportal.services;
import com.group6.webbportal.entities.Trips;

import java.util.List;

public interface TripsService {
    List<Trips> findAll();
    Trips createTrip(Trips trips);
    Trips findById(int id);
    Trips save(Trips trip);
    Trips update(int id, Trips trip);
    void deleteById(int id);
}
