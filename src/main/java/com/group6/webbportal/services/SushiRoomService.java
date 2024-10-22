package com.group6.webbportal.services;

import com.group6.webbportal.entities.SushiRoom;

public interface SushiRoomService {

    SushiRoom findById(Long id);
    SushiRoom updateRoom(SushiRoom sushiRoom, Long id);
}
