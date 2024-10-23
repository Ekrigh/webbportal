package com.group6.webbportal.services;

import com.group6.webbportal.dto.MovieRequestDTO;
import com.group6.webbportal.dto.MovieResponseDTO;
import com.group6.webbportal.entities.Movie;

import java.util.List;

public interface MovieService {

    List<MovieResponseDTO> findAll();

    Movie findById(int id);

    Movie findByIdAndDeletedFalse(int id);

    Movie create(MovieRequestDTO movieRequestDTO);

    void deleteById(int id);
}
