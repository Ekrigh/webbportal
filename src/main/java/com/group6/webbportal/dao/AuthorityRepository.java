package com.group6.webbportal.dao;

import com.group6.webbportal.entities.Authority;
import com.group6.webbportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
