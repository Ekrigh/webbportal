package com.group6.webbportal.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group6.webbportal.entities.User;
import com.group6.webbportal.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository usrRepository) {
        userRepository = usrRepository;
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
