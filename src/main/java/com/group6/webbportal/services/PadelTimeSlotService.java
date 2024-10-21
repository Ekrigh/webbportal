package com.group6.webbportal.services;

import com.group6.webbportal.entities.PadelTimeSlot;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface PadelTimeSlotService {
    List<PadelTimeSlot> findAll();

    PadelTimeSlot findById(int id);

    List<PadelTimeSlot> findAllUnbooked();

    PadelTimeSlot save(PadelTimeSlot timeSlot);

    void deleteById(int id);
}
