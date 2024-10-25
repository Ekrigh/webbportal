package com.group6.webbportal.dao;
import com.group6.webbportal.entities.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface DestinationsRepository extends JpaRepository<Destinations, Integer> {
    //Optional<Destinations> findDestinationByCityAndCountry(String city, String country);
}
