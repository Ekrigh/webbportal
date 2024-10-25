package com.group6.webbportal.services;


import com.group6.webbportal.dao.BookingRepository;
import com.group6.webbportal.dao.TripsRepository;
import com.group6.webbportal.entities.Booking;
import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.Trips;
import com.group6.webbportal.entities.User;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private UserService userService;
    private TripsRepository tripsRepository;
    private CurrencyConversionService currencyConversionService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,CustomerService customerService, UserService userService, TripsRepository tripsRepository, CurrencyConversionService currencyConversionService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.userService = userService;
        this.currencyConversionService = currencyConversionService;
        this.tripsRepository = tripsRepository;
    }



    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("booking with id: " + id + " doesn't exist"));
    }

    @Override
    public List<Booking> findAllByCustomerId(int id, Authentication authentication) {
        Customer customer = customerService.findById(id);
        if (!customer.getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You don't have access to this customer-id.");
        }
        return bookingRepository.findByCustomer(customer);

    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking create(Booking booking, Authentication authentication) {
        // Hämta användaren och kunden baserat på den inloggade användaren
        User username = userService.findByUsername(authentication.getName());
        Customer customer = customerService.findByUser(username);

        // Skapa en ny bokning och sätt kunden
        Booking newBooking = new Booking();
        newBooking.setCustomer(customer);

        return newBooking;
    }


    @Override
    public Booking update(int bookingId, Booking booking , Authentication authentication) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("booking with id: " + bookingId + " doesn't exist"));
        if (!existingBooking.getCustomer().getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You are not allowed to modify this booking.");
        }
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void delete(int bookingId) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("booking with id: " + bookingId + " doesn't exist"));
        bookingRepository.delete(existingBooking);

    }

    public Booking createBooking(Booking bookingDetails, User user) {
        // Hämta kunden baserat på användaren (User)
        Customer customer = customerService.findByUser(user);

        // Hämta resan baserat på tripId som skickas i bodyn
        Trips trip = tripsRepository.findById(bookingDetails.getTrip().getId())
                .orElseThrow(() -> new EntityNotFoundException("Trip with ID " + bookingDetails.getTrip().getId() + " not found"));

        // Skapa en ny bokning
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setTrip(trip);


        // Räkna ut totalpriset baserat på resan
        double totalPriceSEK = trip.getPricePerWeek();

        // Om du har en valutakonverteringstjänst, konvertera från SEK till PLN
        double totalPricePLN = currencyConversionService.convertSEKToPLN(totalPriceSEK);

        // Sätt det totala priset i PLN
        booking.setTotalPricePLN(totalPricePLN);

        // Spara bokningen
        return bookingRepository.save(booking);
    }


}
