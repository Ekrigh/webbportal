package com.group6.webbportal.services;

import com.group6.webbportal.dao.PadelBookingRepository;

import com.group6.webbportal.entities.Customer;
import com.group6.webbportal.entities.PadelBooking;
import com.group6.webbportal.entities.PadelTimeSlot;
import com.group6.webbportal.entities.User;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import com.group6.webbportal.exceptions.TimeSlotAlreadyBookedException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

@Service
public class PadelBookingServiceImpl implements PadelBookingService {
    private PadelBookingRepository padelBookingRepository;
    private PadelTimeSlotService padelTimeSlotService;
    private CustomerService customerService;
    private UserService userService;
    private CurrencyConversionService currencyConversionService;

    @Autowired
    public PadelBookingServiceImpl(PadelBookingRepository padelBookingRepository, PadelTimeSlotService padelTimeSlotService, CustomerService customerService, CurrencyConversionService currencyConversionService, UserService userService) {
        this.padelBookingRepository = padelBookingRepository;
        this.padelTimeSlotService = padelTimeSlotService;
        this.customerService = customerService;
        this.currencyConversionService = currencyConversionService;
        this.userService = userService;
    }



    @Override
    public PadelBooking findById(int id) {
        return padelBookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("booking with id: " + id + " doesn't exist"));
    }

    @Override
    public List<PadelBooking> findAllByCustomerId(int id, Authentication authentication) {
        Customer customer = customerService.findById(id);
        if (!customer.getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You don't have access to this customer-id.");
        }
        return padelBookingRepository.findByCustomer(customer);
    }

    @Transactional
    @Override
    public PadelBooking save(PadelBooking padelBooking) {
        return padelBookingRepository.save(padelBooking);
    }

    @Transactional
    @Override
    public PadelBooking create(PadelBooking padelBooking, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Customer customer = customerService.findByUser(user);

        PadelTimeSlot padelTimeSlot = padelTimeSlotService.findById(padelBooking.getPadelTimeSlot().getId());

        if (padelTimeSlot.getPadelBooking() != null) {
            throw new TimeSlotAlreadyBookedException("Time slot is already booked");
        }

        PadelBooking newBooking = new PadelBooking();
        newBooking.setCustomer(customer);
        newBooking.setPadelTimeSlot(padelTimeSlot);
        newBooking.setAmountOfPlayers(padelBooking.getAmountOfPlayers());
        double totalPriceSek = padelTimeSlot.getPricePerPlayer() * newBooking.getAmountOfPlayers();
        double totalPriceEur = currencyConversionService.convertSEKToEUR(totalPriceSek);
        newBooking.setTotalPrice(totalPriceSek + " SEK / " + totalPriceEur + " EUR.");

        PadelBooking savedBooking = padelBookingRepository.save(newBooking);

        padelTimeSlot.setPadelBooking(savedBooking);
        padelTimeSlotService.save(padelTimeSlot);

        return savedBooking;
    }

    @Transactional
    @Override
    public PadelBooking update(int bookingId, PadelBooking updatedBooking, Authentication authentication) {

        PadelBooking existingBooking = padelBookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + bookingId + " not found."));

        if (!existingBooking.getCustomer().getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You are not allowed to modify this booking.");
        }

        // Find the new time slot
        PadelTimeSlot newTimeSlot = padelTimeSlotService.findById(updatedBooking.getPadelTimeSlot().getId());

        // Check if the time slot is already booked
        if (newTimeSlot.getPadelBooking() != null && !newTimeSlot.equals(existingBooking.getPadelTimeSlot())) {
            throw new TimeSlotAlreadyBookedException("The new time slot is already booked.");
        }

        // If the time slot is updated, update the references
        if (newTimeSlot.getId() != (existingBooking.getPadelTimeSlot().getId())) {
            // Release the old time slot
            PadelTimeSlot oldTimeSlot = existingBooking.getPadelTimeSlot();
            oldTimeSlot.setPadelBooking(null);
            padelTimeSlotService.save(oldTimeSlot);

            // Update to the new time slot
            existingBooking.setPadelTimeSlot(newTimeSlot);
            newTimeSlot.setPadelBooking(existingBooking);
            padelTimeSlotService.save(newTimeSlot);
        }

        // Update other fields such as amount of players
        existingBooking.setAmountOfPlayers(updatedBooking.getAmountOfPlayers());

        // Recalculate the total price
        double totalPriceSek = newTimeSlot.getPricePerPlayer() * existingBooking.getAmountOfPlayers();
        double totalPriceEur = currencyConversionService.convertSEKToEUR(totalPriceSek);
        existingBooking.setTotalPrice(totalPriceSek + " SEK / " + totalPriceEur + " EUR.");

        // Save and return the updated booking
        return padelBookingRepository.save(existingBooking);
    }

    @Transactional
    @Override
    public void deleteBooking(int bookingId) {
        // Find the existing booking by ID
        PadelBooking existingBooking = padelBookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + bookingId + " not found."));

        // Get the associated time slot and release the booking reference
        PadelTimeSlot padelTimeSlot = existingBooking.getPadelTimeSlot();
        padelTimeSlot.setPadelBooking(null);
        padelTimeSlotService.save(padelTimeSlot); // Save the time slot with no associated booking

        // Delete the booking
        padelBookingRepository.delete(existingBooking);
    }
}
