package com.group6.webbportal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class MovieRequestDTO {

    @NotNull(message = "Movie name is required")
    @Size(min = 1, max = 50, message = "Movie name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Duration in minutes is required")
    @Min(value = 1, message = "Duration must be positive")
    private int durationInMinutes;

    @NotNull(message = "Age limit is required")
    private String ageLimit;

    @NotEmpty(message = "At least one genre is required")
    private List<Integer> genreIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
}


