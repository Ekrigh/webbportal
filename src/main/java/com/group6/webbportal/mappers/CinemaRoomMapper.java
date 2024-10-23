package com.group6.webbportal.mappers;

import com.group6.webbportal.dto.CinemaRoomDTO;
import com.group6.webbportal.entities.CinemaRoom;
import org.springframework.stereotype.Component;

@Component
public class CinemaRoomMapper {

    public CinemaRoomDTO toDTO(CinemaRoom cinemaRoom){
        if(cinemaRoom == null){
            return null;
        }

        CinemaRoomDTO cinemaRoomDTO = new CinemaRoomDTO();
        cinemaRoomDTO.setName(cinemaRoom.getName());
        cinemaRoomDTO.setPriceSEK(cinemaRoom.getPriceSEK());
        cinemaRoomDTO.setCapacity(cinemaRoom.getCapacity());
        return cinemaRoomDTO;
    }
}
