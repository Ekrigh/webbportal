package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.SushiDish;
import com.group6.webbportal.services.SushiDishService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group6.webbportal.WebbPortalApplication.logger;

@RestController
@RequestMapping()
public class SushiDishController {

    private SushiDishService sushiDishService;

    public SushiDishController(SushiDishService sushiDishServ){
        sushiDishService = sushiDishServ;
    }

    @GetMapping("/sushis")
    public List<SushiDish>findAll(){
        return sushiDishService.findAll();
    }

    @PostMapping("/sushis/admin")
    public SushiDish addSushi(@RequestBody SushiDish sushiDish, Authentication authentication){
        sushiDish.setId(0L);
        SushiDish s = sushiDishService.save(sushiDish);
        logger.info("User '{}' with role(s){}, added sushi dish '{}'.",authentication.getName(), authentication.getAuthorities(),sushiDish.getName());
        return s;
    }

    @DeleteMapping("/sushis/{id}")
    public String deleteSushi(@PathVariable int id, Authentication authentication){
        SushiDish sushiDish = sushiDishService.findById(id);
        if(sushiDish == null){
            throw new RuntimeException("Sushi dish with id " + id + " not found");
        }
        sushiDishService.deleteById(id);
        logger.info("User '{}' with role(s){}, deleted sushi dish '{}'.", authentication.getName(), authentication.getAuthorities(), sushiDish.getName());
        return "Sushi dish with id " + id + " deleted";
    }
}
