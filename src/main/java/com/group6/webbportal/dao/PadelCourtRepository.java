package com.group6.webbportal.dao;

import com.group6.webbportal.entities.PadelCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PadelCourtRepository extends JpaRepository<PadelCourt, Integer> {
}
