package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.SushiRoom;
import com.group6.webbportal.services.SushiRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
@RequestMapping()
public class SushiRoomController {

    private SushiRoomService sushiRoomService;

    public SushiRoomController(SushiRoomService sushiRoomServ){
        sushiRoomService = sushiRoomServ;
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<SushiRoom> updateRoom(@PathVariable int id, @RequestBody SushiRoom sushiRoom, Authentication authentication){
        if (sushiRoom == null){
            throw new RuntimeException("Sushi room id " + id + " does not exist");
        }
        logger.info("{} with role(s){}, updated sushi room {}.",authentication.getName(), authentication.getAuthorities(), sushiRoomService.findById((long) id).getName());
        return ResponseEntity.ok(sushiRoomService.updateRoom(sushiRoom, (long) id));
    }
}