package com.group6.webbportal.exceptions;

import java.util.Set;

public class GenresNotFoundException extends RuntimeException{
    public GenresNotFoundException(Set<Integer> ids) {
        super("Genres with ids " + ids + " were not found");
    }
}
