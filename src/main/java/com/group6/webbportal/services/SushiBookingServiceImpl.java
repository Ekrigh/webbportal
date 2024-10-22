package com.group6.webbportal.services;

import com.group6.webbportal.dao.*;
import com.group6.webbportal.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SushiBookingServiceImpl implements SushiBookingService{

    private SushiRoomRepository sushiRoomRepository;
    private SushiBookingRepository sushiBookingRepository;
    private CustomerRepository customerRepository;
    private SushiDishRepository sushiDishRepository;
    private DishQuantityRepository dishQuantityRepository;

    @Autowired
    public SushiBookingServiceImpl(SushiRoomRepository sushiRoomRepo, SushiBookingRepository sushiBookingRepo, CustomerRepository customerRepo, SushiDishRepository sushiDishRepo,
    DishQuantityRepository dishQuantityRepo) {
        sushiRoomRepository = sushiRoomRepo;
        sushiBookingRepository = sushiBookingRepo;
        customerRepository = customerRepo;
        sushiDishRepository = sushiDishRepo;
        dishQuantityRepository = dishQuantityRepo;
    }

    @Override
    public SushiBooking makeBooking(SushiBooking sushiBooking, List<DishQuantity> bookingDishes) {
        Customer customer = customerRepository.findById(sushiBooking.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        SushiRoom room = sushiRoomRepository.findById(sushiBooking.getSushiRoom().getId())
                .orElseThrow(() -> new IllegalArgumentException("Room does not exist"));

        if (sushiBooking.getGuestCount() > room.getMaxGuests()) {
            throw new IllegalArgumentException("Guest count exceeds the room's capacity");
        }

        sushiBooking.setSushiRoom(room);

        sushiBooking.setCustomer(customer);

        sushiBooking = sushiBookingRepository.save(sushiBooking);

        List<DishQuantity> dishQuantities = new ArrayList<>();

        double totalPrice = 0.0;

        for (DishQuantity dishQuantity : sushiBooking.getDishQuantities()) {
            SushiDish sushiDish = sushiDishRepository.findById(dishQuantity.getSushiDish().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Dish with ID " + dishQuantity.getSushiDish().getId() + " does not exist"));

            // Skapa ett nytt DishQuantity-objekt och koppla till rätt och bokning
            DishQuantity newDishQuantity = new DishQuantity();
            newDishQuantity.setSushiBooking(sushiBooking);
            newDishQuantity.setSushiDish(sushiDish);
            newDishQuantity.setQuantity(dishQuantity.getQuantity());
            DishQuantity savedDishQuantity = dishQuantityRepository.save(newDishQuantity);

            dishQuantities.add(savedDishQuantity);

            totalPrice += sushiDish.getPriceSEK() * dishQuantity.getQuantity();
        }

        sushiBooking.setTotalPriceSEK(totalPrice);

        sushiBooking.setDishQuantities(dishQuantities);

        return sushiBookingRepository.save(sushiBooking);
    }


    @Override
    public SushiBooking updateBooking(SushiBooking updatedBooking, int bookingId) {
        SushiBooking existingBooking = sushiBookingRepository.findById((long) bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking with ID " + bookingId + " does not exist"));

        Customer customer = customerRepository.findById(updatedBooking.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        SushiRoom room = sushiRoomRepository.findById(updatedBooking.getSushiRoom().getId())
                .orElseThrow(() -> new IllegalArgumentException("Room does not exist"));

        if (updatedBooking.getGuestCount() > room.getMaxGuests()) {
            throw new IllegalArgumentException("Guest count exceeds the room's capacity");
        }

        existingBooking.setGuestCount(updatedBooking.getGuestCount());
        existingBooking.setSushiRoom(room);
        existingBooking.setCustomer(customer);

        List<DishQuantity> updatedDishQuantities = new ArrayList<>();
        double totalPrice = 0.0;

        dishQuantityRepository.deleteAll(existingBooking.getDishQuantities());

        for (DishQuantity dishQuantity : updatedBooking.getDishQuantities()) {
            SushiDish sushiDish = sushiDishRepository.findById(dishQuantity.getSushiDish().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Dish with ID " + dishQuantity.getSushiDish().getId() + " does not exist"));

            DishQuantity newDishQuantity = new DishQuantity();
            newDishQuantity.setSushiBooking(existingBooking);
            newDishQuantity.setSushiDish(sushiDish);
            newDishQuantity.setQuantity(dishQuantity.getQuantity());

            DishQuantity savedDishQuantity = dishQuantityRepository.save(newDishQuantity);
            updatedDishQuantities.add(savedDishQuantity);

            totalPrice += sushiDish.getPriceSEK() * dishQuantity.getQuantity();
        }

        existingBooking.setTotalPriceSEK(totalPrice);
        existingBooking.setDishQuantities(updatedDishQuantities);

        return sushiBookingRepository.save(existingBooking);
    }

    @Override
    public List<SushiBooking> getBookingsByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalArgumentException("Customer with ID " + customerId + " does not exist"));
        return sushiBookingRepository.findAllByCustomer(customer);
    }
}
