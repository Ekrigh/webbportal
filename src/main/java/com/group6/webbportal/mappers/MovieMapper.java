package com.group6.webbportal.mappers;

import com.group6.webbportal.dto.MovieRequestDTO;
import com.group6.webbportal.dto.MovieResponseDTO;
import com.group6.webbportal.entities.Genre;
import com.group6.webbportal.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequestDTO movieRequestDTO, Set<Genre> genres) {
        Movie movie = new Movie();
        movie.setName(movieRequestDTO.getName());
        movie.setDurationInMinutes(movieRequestDTO.getDurationInMinutes());
        movie.setAgeLimit(movieRequestDTO.getAgeLimit());
        movie.setGenres(genres); // Genres will be fetched based on genreIds
        return movie;
    }

    // Mapping from Movie entity to MovieResponseDTO
    public MovieResponseDTO toResponseDTO(Movie movie) {
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setName(movie.getName());
        movieResponseDTO.setDurationInMinutes(movie.getDurationInMinutes());
        movieResponseDTO.setAgeLimit(movie.getAgeLimit());

        // Map genres to their names
        movieResponseDTO.setGenreNames(movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList()));

        return movieResponseDTO;
    }
}
