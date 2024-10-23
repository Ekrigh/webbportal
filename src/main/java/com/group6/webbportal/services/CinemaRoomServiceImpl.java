package com.group6.webbportal.services;

import com.group6.webbportal.dao.CinemaBookingRepository;
import com.group6.webbportal.dao.CinemaRoomRepository;
import com.group6.webbportal.dto.CinemaRoomDTO;
import com.group6.webbportal.entities.CinemaRoom;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaRoomServiceImpl implements CinemaRoomService{

    private final CinemaRoomRepository roomRepository;

    private final CinemaBookingRepository bookingRepository;

    @Autowired
    public CinemaRoomServiceImpl(CinemaRoomRepository roomRepository, CinemaBookingRepository bookingRepository){
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public CinemaRoom findById(int id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " +id+ " not found"));
    }

    @Override
    public CinemaRoom update(CinemaRoomDTO updatedRoom, int id){
        CinemaRoom room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " +id+ " not found"));

        if(bookingRepository.existsByRoomAndNrOfGuestsGreaterThan(room, updatedRoom.getCapacity())){
            throw new RuntimeException("Cannot update capacity as there are existing bookings exceeding the new capacity.");
        }
        room.setName(updatedRoom.getName());
        room.setPriceSEK(updatedRoom.getPriceSEK());
        room.setCapacity(updatedRoom.getCapacity());

        return roomRepository.save(room);
    }
}
