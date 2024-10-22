package com.group6.webbportal.services;

import com.group6.webbportal.dao.SushiDishRepository;
import com.group6.webbportal.entities.SushiDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SushiDishServiceImpl implements SushiDishService {

    private SushiDishRepository sushiDishRepository;

    @Autowired
    public SushiDishServiceImpl(SushiDishRepository sushiDishRepo) {
        sushiDishRepository = sushiDishRepo;
    }

    @Override
    public List<SushiDish> findAll() {
        return sushiDishRepository.findAll();
    }

    @Override
    public SushiDish findById(int id) {
        Optional<SushiDish> s = sushiDishRepository.findById((long) id);
        SushiDish sushiDish = null;
        if (s.isPresent()) {
            sushiDish = s.get();
        } else {
            throw new RuntimeException("Sushi with id: " + id + " was not found");
        }
        return sushiDish;
    }

    @Override
    public SushiDish save(SushiDish sushiDish) {
        return sushiDishRepository.save(sushiDish);
    }

    @Override
    public void deleteById(int id) {
        sushiDishRepository.deleteById((long) id);
    }
}
