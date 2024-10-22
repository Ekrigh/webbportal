package com.group6.webbportal.services;

import com.group6.webbportal.entities.SushiDish;

import java.util.List;

public interface SushiDishService {

    List<SushiDish> findAll();
    SushiDish findById(int id);
    SushiDish save(SushiDish sushiDish);
    void deleteById(int id);
}
