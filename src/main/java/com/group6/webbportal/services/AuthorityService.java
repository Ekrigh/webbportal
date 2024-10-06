package com.group6.webbportal.services;

import com.group6.webbportal.entities.Authority;
import com.group6.webbportal.entities.User;

import java.util.List;

public interface AuthorityService {
    Authority save(Authority authority);
    void delete(Authority authority);
}
