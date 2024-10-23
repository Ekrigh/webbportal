package com.group6.webbportal.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cinema_bookings")
public class CinemaBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;

    @Column(name = "booking_nr", unique = true, nullable = false)
    private String bookingNr;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private CinemaRoom room;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_price_sek", nullable = false)
    private double totalPriceSEK;

    @Column(name = "total_price_usd", nullable = false)
    private double totalPriceUSD;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "use_own_speaker", nullable = false)
    private boolean useOwnSpeaker;

    @Column(name = "nr_of_guests", nullable = false)
    private int nrOfGuests;

    public CinemaBooking(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingNr() {
        return bookingNr;
    }

    public void setBookingNr(String bookingNr) {
        this.bookingNr = bookingNr;
    }

    public CinemaRoom getRoom() {
        return room;
    }

    public void setRoom(CinemaRoom room) {
        this.room = room;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
    }

    public double getTotalPriceUSD() {
        return totalPriceUSD;
    }

    public void setTotalPriceUSD(double totalPriceUSD) {
        this.totalPriceUSD = totalPriceUSD;
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

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isUseOwnSpeaker() {
        return useOwnSpeaker;
    }

    public void setUseOwnSpeaker(boolean useOwnSpeaker) {
        this.useOwnSpeaker = useOwnSpeaker;
    }

    public int getNrOfGuests() {
        return nrOfGuests;
    }

    public void setNrOfGuests(int nrOfGuests) {
        this.nrOfGuests = nrOfGuests;
    }
}
