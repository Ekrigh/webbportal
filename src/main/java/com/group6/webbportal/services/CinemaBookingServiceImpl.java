package com.group6.webbportal.services;

import com.group6.webbportal.dao.CinemaBookingRepository;
import com.group6.webbportal.dto.CinemaBookingRequestDTO;
import com.group6.webbportal.entities.*;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import com.group6.webbportal.exceptions.InvalidBookingException;
import com.group6.webbportal.mappers.CinemaBookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class CinemaBookingServiceImpl implements CinemaBookingService{

    private final CinemaBookingRepository bookingRepository;

    private final MovieService movieService;

    private final CinemaRoomService roomService;

    private final CustomerService customerService;

    private final UserService userService;

    private final CurrencyConversionService currencyConversionService;

    private final CinemaBookingMapper bookingMapper;

    @Autowired
    public CinemaBookingServiceImpl(CinemaBookingRepository bookingRepository,
                                    MovieService movieService,
                                    CinemaRoomService roomService,
                                    CustomerService customerService,
                                    UserService userService,
                                    CurrencyConversionService currencyConversionService,
                                    CinemaBookingMapper bookingMapper){
        this.bookingRepository = bookingRepository;
        this.movieService = movieService;
        this.roomService = roomService;
        this.customerService = customerService;
        this.userService = userService;
        this.currencyConversionService = currencyConversionService;
        this.bookingMapper = bookingMapper;

    }
    public CinemaBooking findById(int id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cinema booking with id " +id+ " not found."));
    }

    @Transactional
    @Override
    public CinemaBooking createCinemaBooking(CinemaBookingRequestDTO bookingRequestDTO, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        Customer customer = customerService.findByUser(user);

        validateSpeakerAndMovie(bookingRequestDTO);
        validateRoomCapacity(bookingRequestDTO);
        validateStartTime(bookingRequestDTO.getStartTime());

        CinemaRoom room = roomService.findById(bookingRequestDTO.getRoomId());
        LocalTime endTime = bookingRequestDTO.getStartTime().plusHours(bookingRequestDTO.getDurationInHours());
        checkRoomAvailability(room, bookingRequestDTO.getDate(), bookingRequestDTO.getStartTime(), endTime);

        Movie movie = fetchMovieIfApplicable(bookingRequestDTO);

        CinemaBooking booking = bookingMapper.toEntity(bookingRequestDTO, room, movie, customer, endTime);

        booking.setTotalPriceSEK(room.getPriceSEK());
        double totalPriceUSD = currencyConversionService.convertSEKToUSD(room.getPriceSEK());
        booking.setTotalPriceUSD(totalPriceUSD);

        booking.setBookingNr(generateBookingNumber());

        return bookingRepository.save(booking);
    }

    @Transactional
    @Override
    public CinemaBooking updateCinemaBooking(CinemaBookingRequestDTO bookingRequestDTO, int bookingId,
                                             Authentication authentication){

        CinemaBooking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Cinema booking with id " +bookingId+ " not found."));

        if (!existingBooking.getCustomer().getUser().getUsername().equals(authentication.getName())) {
            throw new AccessDeniedException("You are not allowed to modify this booking.");
        }


        validateSpeakerAndMovie(bookingRequestDTO);
        validateRoomCapacity(bookingRequestDTO);
        validateStartTime(bookingRequestDTO.getStartTime());

        CinemaRoom room = roomService.findById(bookingRequestDTO.getRoomId());
        LocalTime endTime = bookingRequestDTO.getStartTime().plusHours(bookingRequestDTO.getDurationInHours());
        checkRoomAvailability(room, bookingRequestDTO.getDate(), bookingRequestDTO.getStartTime(), endTime, bookingId);

        Movie movie;
        if (bookingRequestDTO.isUseOwnSpeaker()) {
            movie = null;
        } else if (existingBooking.getMovie() == null ||
                !bookingRequestDTO.getMovieId().equals(existingBooking.getMovie().getId())) {
            movie = fetchMovieIfApplicable(bookingRequestDTO);
        } else {
            movie = existingBooking.getMovie();
        }
        existingBooking.setRoom(room);
        existingBooking.setDate(bookingRequestDTO.getDate());
        existingBooking.setStartTime(bookingRequestDTO.getStartTime());
        existingBooking.setEndTime(endTime);
        existingBooking.setUseOwnSpeaker(bookingRequestDTO.isUseOwnSpeaker());
        existingBooking.setNrOfGuests(bookingRequestDTO.getNrOfGuests());
        existingBooking.setMovie(movie);
        existingBooking.setTotalPriceSEK(room.getPriceSEK());
        double totalPriceUSD = currencyConversionService.convertSEKToUSD(room.getPriceSEK());
        existingBooking.setTotalPriceUSD(totalPriceUSD);

        return bookingRepository.save(existingBooking);
    }

    private void validateSpeakerAndMovie(CinemaBookingRequestDTO bookingRequestDTO) {
        if (!bookingRequestDTO.isUseOwnSpeaker() && bookingRequestDTO.getMovieId() == null) {
            throw new InvalidBookingException("A movie must be selected if not using own speaker.");
        }
        if (bookingRequestDTO.isUseOwnSpeaker() && bookingRequestDTO.getMovieId() != null) {
            throw new InvalidBookingException("Cannot select a movie when using own speaker.");
        }
    }

    private void validateRoomCapacity(CinemaBookingRequestDTO bookingRequestDTO) {
        CinemaRoom room = roomService.findById(bookingRequestDTO.getRoomId());
        if (bookingRequestDTO.getNrOfGuests() > room.getCapacity()) {
            throw new InvalidBookingException("Number of guests exceeds room capacity of " + room.getCapacity());
        }
    }

    private void validateStartTime(LocalTime startTime) {
        LocalTime maxAllowedStartTime = LocalTime.of(19, 0);
        if (startTime.isAfter(maxAllowedStartTime)) {
            throw new InvalidBookingException("Start time cannot be later than 19:00.");
        }
    }

    private void checkRoomAvailability(CinemaRoom room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (bookingRepository.existsByRoomAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
                room, date, endTime, startTime)) {
            throw new InvalidBookingException("Room already booked");
        }
    }
    private void checkRoomAvailability(CinemaRoom room, LocalDate date, LocalTime startTime, LocalTime endTime, int id) {
        if (bookingRepository.existsByRoomAndDateAndStartTimeLessThanAndEndTimeGreaterThanAndIdNot(
                room, date, endTime, startTime, id)) {
            throw new InvalidBookingException("Room already booked");
        }
    }

    private Movie fetchMovieIfApplicable(CinemaBookingRequestDTO bookingRequestDTO) {
        if (!bookingRequestDTO.isUseOwnSpeaker()) {
            Movie movie = movieService.findByIdAndDeletedFalse(bookingRequestDTO.getMovieId());
            int bookingDurationInMinutes = bookingRequestDTO.getDurationInHours() * 60;
            if (movie.getDurationInMinutes() > bookingDurationInMinutes) {
                throw new InvalidBookingException("Booking duration must be at least as long as the movie duration.");
            }
            return movie;
        }
        return null;
    }

    private String generateBookingNumber() {
        // Generate UUID and take only a subset of it (e.g., first 8 characters)
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }




}
