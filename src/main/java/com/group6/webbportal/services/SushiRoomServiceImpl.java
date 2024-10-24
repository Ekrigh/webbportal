package com.group6.webbportal.services;

import com.group6.webbportal.dao.SushiRoomRepository;
import com.group6.webbportal.entities.SushiRoom;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SushiRoomServiceImpl implements SushiRoomService {

    SushiRoomRepository sushiRoomRepository;

    public SushiRoomServiceImpl(SushiRoomRepository sushiRoomRepo){
        sushiRoomRepository = sushiRoomRepo;
    }

    @Override
    public SushiRoom findById(Long id) {
        return sushiRoomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sushi room with id: " + id + " doesn't exist"));
    }

    @Override
    public SushiRoom updateRoom(SushiRoom sushiRoom, Long id) {
        return sushiRoomRepository.findById(id).map(room -> {
            room.setName(sushiRoom.getName());
            room.setMaxGuests(sushiRoom.getMaxGuests());
            room.setTechnicalEquipment(sushiRoom.getTechnicalEquipment());
            return sushiRoomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with id " + id));
    }
}