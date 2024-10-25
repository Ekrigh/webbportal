package com.group6.webbportal.services;




import com.group6.webbportal.entities.Destinations;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DestinationsService {
    Destinations findById(int id);
    List<Destinations> findAll();
    Destinations save (Destinations destinations);
    Destinations updateDestination(Destinations destinations, int id);
    Destinations createDestination(Destinations destinations);
    ResponseEntity<String> deleteById(int id);

}