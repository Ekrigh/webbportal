package com.group6.webbportal.dao;

import com.group6.webbportal.entities.DishQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishQuantityRepository extends JpaRepository<DishQuantity, Long> {
}
