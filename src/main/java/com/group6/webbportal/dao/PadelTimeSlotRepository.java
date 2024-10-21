package com.group6.webbportal.dao;
import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.PadelTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PadelTimeSlotRepository extends JpaRepository<PadelTimeSlot, Integer> {

    List<PadelTimeSlot> findByPadelBookingIsNullOrderByStartTimeAscEndTimeAscPadelCourtNameAsc();

    List<PadelTimeSlot> findAllByOrderByStartTimeAsc();



}
