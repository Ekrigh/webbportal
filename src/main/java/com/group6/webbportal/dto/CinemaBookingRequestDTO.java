package com.group6.webbportal.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class CinemaBookingRequestDTO {

    @NotNull(message = "Provide a room id")
    private Integer roomId;
    private Integer movieId;
    @NotNull(message = "You have to choose true or false on useOwnSpeaker")
    private Boolean useOwnSpeaker;
    @NotNull(message = "Provide a date")
    @Future(message = "The date must be in the furure")
    private LocalDate date;
    @NotNull(message = "Provide a start time")
    private LocalTime startTime;
    @NotNull(message = "Provide number of hours for the booking")
    @Min(value = 1, message = "You have to book at least 1 hour")
    @Max(value = 4, message = "The maximum amount of time for a booking is 4 hours")
    private Integer durationInHours;
    @NotNull(message = "Provide number of guests")
    @Min(value = 10, message = "The minimum number of guests is 10")
    private Integer nrOfGuests;

    public CinemaBookingRequestDTO(){}

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Boolean isUseOwnSpeaker() {
        return useOwnSpeaker;
    }

    public void setUseOwnSpeaker(Boolean useOwnSpeaker) {
        this.useOwnSpeaker = useOwnSpeaker;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public Integer getNrOfGuests() {
        return nrOfGuests;
    }

    public void setNrOfGuests(Integer nrOfGuests) {
        this.nrOfGuests = nrOfGuests;
    }
}
