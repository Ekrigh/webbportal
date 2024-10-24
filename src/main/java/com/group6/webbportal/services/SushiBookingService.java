package com.group6.webbportal.services;

import com.group6.webbportal.entities.*;

import java.util.List;

public interface SushiBookingService {

    SushiBooking makeBooking(SushiBooking sushiBooking, List<DishQuantity> bookingDishes);

    SushiBooking updateBooking(SushiBooking sushiBooking, int id);

    List<SushiBooking> getBookingsByCustomerId(int customerId);
}