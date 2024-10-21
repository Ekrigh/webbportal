package com.group6.webbportal.services;


import com.group6.webbportal.entities.User;

public interface UserService{
    User save(User user);
    User findByUsername(String username);
}
