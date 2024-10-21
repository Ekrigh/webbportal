package com.group6.webbportal.services;

import com.group6.webbportal.dao.PadelTimeSlotRepository;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.PadelTimeSlot;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PadelTimeSlotServiceImpl implements PadelTimeSlotService {

    private final PadelTimeSlotRepository padelTimeSlotRepository;

    @Autowired
    public PadelTimeSlotServiceImpl(PadelTimeSlotRepository padelTimeSlotRepository) {
        this.padelTimeSlotRepository = padelTimeSlotRepository;
    }

    @Override
    public List<PadelTimeSlot> findAll() {
        return padelTimeSlotRepository.findAllByOrderByStartTimeAsc();
    }

    @Override
    public PadelTimeSlot findById(int id) {
        return padelTimeSlotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Timeslot with id: " + id + " doesn't exist"));
    }

    @Override
    public List<PadelTimeSlot> findAllUnbooked() {
        return padelTimeSlotRepository.findByPadelBookingIsNullOrderByStartTimeAscEndTimeAscPadelCourtNameAsc();
    }

    @Override
    public PadelTimeSlot save(PadelTimeSlot timeSlot) {
        return padelTimeSlotRepository.save(timeSlot);
    }

    @Override
    public void deleteById(int id) {
        padelTimeSlotRepository.deleteById(id);
    }
}

