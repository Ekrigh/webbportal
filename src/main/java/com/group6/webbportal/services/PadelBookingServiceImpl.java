package com.group6.webbportal.services;

import com.group6.webbportal.dao.PadelBookingRepository;

import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PadelBookingServiceImpl implements PadelBookingService {

    private PadelBookingRepository padelBookingRepository;

    @Autowired
    public PadelBookingServiceImpl(PadelBookingRepository padelBookingRepository) {
        this.padelBookingRepository = padelBookingRepository;
    }

    @Override
    public List<PadelBooking> findAll() {
        return padelBookingRepository.findAllByOrderByStartTime();
    }

    @Override
    public List<PadelBooking> findAllbyUser(User user) {
        return padelBookingRepository.findAllByUser(user);
    }

    @Override
    public List<PadelBooking> findAllByAvailability() {
        return padelBookingRepository.findByUserIsNull();

    }

    @Transactional
    @Override
    public PadelBooking save(PadelBooking padelBooking) {
        return padelBookingRepository.save(padelBooking);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        padelBookingRepository.deleteById(id);
    }

  /*  @Transactional
    @Override
    public void updateById(int id) {
        padelBookingRepository
    }*/
}
