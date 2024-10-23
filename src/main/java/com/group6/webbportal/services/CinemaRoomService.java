package com.group6.webbportal.services;

import com.group6.webbportal.dto.CinemaRoomDTO;
import com.group6.webbportal.entities.CinemaRoom;

public interface CinemaRoomService {

    CinemaRoom findById(int id);

    CinemaRoom update(CinemaRoomDTO roomDTO, int id);
}
