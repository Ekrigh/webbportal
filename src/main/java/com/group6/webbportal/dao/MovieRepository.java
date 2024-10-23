package com.group6.webbportal.dao;

import com.group6.webbportal.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findAllByDeletedIsFalse();

    Optional<Movie> findMovieByIdAndDeletedIsFalse(int id);
}
