package com.group6.webbportal.services;

import com.group6.webbportal.entities.PadelCourt;

public interface PadelCourtService {

    PadelCourt save(PadelCourt padelCourt);

    void deleteById(int id);

    PadelCourt create(PadelCourt padelCourt);

    PadelCourt updateById(int id, PadelCourt updatedCourt);
}

