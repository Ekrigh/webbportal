package com.group6.webbportal.controllers;

import com.group6.webbportal.entities.SushiOrder;
import com.group6.webbportal.services.SushiOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.group6.webbportal.WebbPortalApplication.logger;


@RestController
@RequestMapping("api/v1")
public class SushiOrderController {

    private final SushiOrderService sushiOrderService;

    public SushiOrderController(SushiOrderService sushiOrderServ){
        sushiOrderService = sushiOrderServ;
    }

    @PostMapping("/sushis/user")
    public ResponseEntity<SushiOrder> createOrder(@RequestBody SushiOrder order, Authentication authentication) {
        SushiOrder savedOrder = sushiOrderService.createOrder(order);
        logger.info("{} with role(s){}, made an order with id {}.", authentication.getName(), authentication.getAuthorities(), order.getId());
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}