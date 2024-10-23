package com.group6.webbportal.controllers;

import com.group6.webbportal.dto.MovieRequestDTO;
import com.group6.webbportal.dto.MovieResponseDTO;
import com.group6.webbportal.entities.Movie;
import com.group6.webbportal.mappers.MovieMapper;
import com.group6.webbportal.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static com.group6.webbportal.WebbPortalApplication.logger;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper){

        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping("/movies")
    public List<MovieResponseDTO> findAllMovies() {
        return movieService.findAll();
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieResponseDTO> createMovie(
            @RequestBody @Valid MovieRequestDTO movieRequestDTO,
            Authentication authentication) {

        Movie movie = movieService.create(movieRequestDTO);

        logger.info("{} {} added movie [{}].",
                authentication.getName(), authentication.getAuthorities(), movie.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(movieMapper.toResponseDTO(movie));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id, Authentication authentication) {
        movieService.deleteById(id);
        String movieName = movieService.findById(id).getName();
        logger.info("{} {} has removed movie [{}].",
                authentication.getName(), authentication.getAuthorities(), movieName);

        return ResponseEntity.noContent().build();
    }
}
