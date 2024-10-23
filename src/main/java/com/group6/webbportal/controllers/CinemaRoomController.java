package com.group6.webbportal.controllers;

import com.group6.webbportal.dto.CinemaRoomDTO;
import com.group6.webbportal.entities.CinemaRoom;
import com.group6.webbportal.mappers.CinemaRoomMapper;
import com.group6.webbportal.services.CinemaRoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaRoomController {

    private final CinemaRoomService roomService;

    private final CinemaRoomMapper roomMapper;

    public CinemaRoomController(CinemaRoomService roomService, CinemaRoomMapper roomMapper){
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<CinemaRoomDTO> updateRoom(
            @Valid @RequestBody CinemaRoomDTO updatedRoom, @PathVariable int id, Authentication authentication){
        CinemaRoom room = roomService.update(updatedRoom, id);
        logger.info("{} {} updated cinema room [{}].",
                authentication.getName(), authentication.getAuthorities(), room.getName());
        return ResponseEntity.ok(roomMapper.toDTO(room));
    }
}
