package com.group6.webbportal.services;

import com.group6.webbportal.dao.AuthorityRepository;
import com.group6.webbportal.entities.Authority;
import com.group6.webbportal.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authRepository) {
        authorityRepository = authRepository;
    }

    @Transactional
    @Override
    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Transactional
    @Override
    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

}
