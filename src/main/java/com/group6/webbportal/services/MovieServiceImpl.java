package com.group6.webbportal.services;

import com.group6.webbportal.dao.GenreRepository;
import com.group6.webbportal.dao.MovieRepository;
import com.group6.webbportal.dto.MovieRequestDTO;
import com.group6.webbportal.dto.MovieResponseDTO;
import com.group6.webbportal.entities.Genre;
import com.group6.webbportal.entities.Movie;
import com.group6.webbportal.exceptions.EntityNotFoundException;
import com.group6.webbportal.exceptions.GenresNotFoundException;
import com.group6.webbportal.mappers.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository, MovieMapper movieMapper){
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieResponseDTO> findAll(){
        return movieRepository.findAllByDeletedIsFalse().stream()
                .map(movieMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Movie create(MovieRequestDTO movieRequestDTO){
        Set<Integer> genreIds = new HashSet<>(movieRequestDTO.getGenreIds());
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));

        if (genres.size() != genreIds.size()) {
            Set<Integer> foundGenreIds = genres.stream()
                    .map(Genre::getId)
                    .collect(Collectors.toSet());
            genreIds.removeAll(foundGenreIds);

            throw new GenresNotFoundException(genreIds);
        }
        Movie movie = movieMapper.toEntity(movieRequestDTO, genres);

        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(int id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " +id+ " not found"));

        movie.setDeleted(true);
        movieRepository.save(movie);

    }
    @Override
    public Movie findByIdAndDeletedFalse(int id) {
        return movieRepository.findMovieByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " +id+ " not found"));
    }

    @Override
    public Movie findById(int id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " +id+ " not found"));
    }
}
