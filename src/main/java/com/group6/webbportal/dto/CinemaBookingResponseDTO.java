package com.group6.webbportal.dto;

import java.time.LocalDate;

public class CinemaBookingResponseDTO {

    private String bookingNumber;
    private String customerEmail;
    private String roomName;
    private String movieName;
    private Integer nrOfGuests;
    private LocalDate date;
    private Integer durationInHours;

    private String totalPriceSEK;
    private String totalPriceUSD;

    public CinemaBookingResponseDTO(){}

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getNrOfGuests() {
        return nrOfGuests;
    }

    public void setNrOfGuests(Integer nrOfGuests) {
        this.nrOfGuests = nrOfGuests;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(String totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }

    public String getTotalPriceUSD() {
        return totalPriceUSD;
    }

    public void setTotalPriceUSD(String totalPriceUSD) {
        this.totalPriceUSD = totalPriceUSD;
    }


}
