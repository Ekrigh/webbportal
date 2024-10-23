package com.group6.webbportal.dao;

import com.group6.webbportal.entities.CinemaBooking;
import com.group6.webbportal.entities.CinemaRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CinemaBookingRepository extends JpaRepository<CinemaBooking, Integer> {

    boolean existsByRoomAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
            CinemaRoom room, LocalDate date, LocalTime endTime, LocalTime startTime);

    boolean existsByRoomAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndIdNot(
            CinemaRoom room, LocalDate date, LocalTime endTime, LocalTime startTime, int id);

    boolean existsByRoomAndNrOfGuestsGreaterThan(
            CinemaRoom room, int nrOfGuests);


}
