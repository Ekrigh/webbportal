package com.group6.webbportal.dao;

import com.group6.webbportal.entities.SushiOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SushiOrderRepository extends JpaRepository <SushiOrder, Long> {
}
