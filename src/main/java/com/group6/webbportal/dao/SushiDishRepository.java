package com.group6.webbportal.dao;

import com.group6.webbportal.entities.SushiDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SushiDishRepository extends JpaRepository<SushiDish, Long> {
}