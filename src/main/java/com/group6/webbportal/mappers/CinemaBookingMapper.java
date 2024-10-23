package com.group6.webbportal.mappers;

import com.group6.webbportal.dto.CinemaBookingRequestDTO;
import com.group6.webbportal.dto.CinemaBookingResponseDTO;
import com.group6.webbportal.entities.CinemaBooking;
import com.group6.webbportal.entities.CinemaRoom;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.Movie;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class CinemaBookingMapper {

    public CinemaBooking toEntity(CinemaBookingRequestDTO cinemaBookingRequestDTO,
                                  CinemaRoom room,
                                  Movie movie,
                                  Customer customer,
                                  LocalTime endTime){
        if (cinemaBookingRequestDTO == null){
            return null;
        }

        CinemaBooking booking = new CinemaBooking();
        booking.setRoom(room);
        booking.setDate(cinemaBookingRequestDTO.getDate());
        booking.setStartTime(cinemaBookingRequestDTO.getStartTime());
        booking.setEndTime(endTime);
        booking.setUseOwnSpeaker(cinemaBookingRequestDTO.isUseOwnSpeaker());
        booking.setNrOfGuests(cinemaBookingRequestDTO.getNrOfGuests());
        booking.setCustomer(customer);

        if(!cinemaBookingRequestDTO.isUseOwnSpeaker()){
            booking.setMovie(movie);
        }
        return booking;
    }

    public CinemaBookingResponseDTO toDTO(CinemaBooking booking){
        if (booking == null) {
            return null;
        }

        CinemaBookingResponseDTO cinemaBookingResponseDTO = new CinemaBookingResponseDTO();
        cinemaBookingResponseDTO.setBookingNumber(booking.getBookingNr());
        cinemaBookingResponseDTO.setCustomerEmail(booking.getCustomer().getEmail());
        cinemaBookingResponseDTO.setRoomName(booking.getRoom().getName());
        cinemaBookingResponseDTO.setMovieName(booking.getMovie() != null ? booking.getMovie().getName() : null);
        cinemaBookingResponseDTO.setNrOfGuests(booking.getNrOfGuests());
        cinemaBookingResponseDTO.setDate(booking.getDate());
        cinemaBookingResponseDTO.setDurationInHours((int) Duration.between(booking.getStartTime(), booking.getEndTime()).toHours());
        cinemaBookingResponseDTO.setTotalPriceSEK(String.format("%.2f", booking.getTotalPriceSEK()));
        cinemaBookingResponseDTO.setTotalPriceUSD(String.format("%.2f", booking.getTotalPriceUSD())); 

        return cinemaBookingResponseDTO;
    }
}
