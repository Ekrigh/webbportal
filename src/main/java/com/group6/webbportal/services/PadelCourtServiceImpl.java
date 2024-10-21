package com.group6.webbportal.services;

import com.group6.webbportal.dao.PadelCourtRepository;
import com.group6.webbportal.entities.PadelCourt;
import com.group6.webbportal.exceptions.CourtAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PadelCourtServiceImpl implements PadelCourtService {


    private PadelCourtRepository padelCourtRepository;

    @Autowired
    public PadelCourtServiceImpl(PadelCourtRepository padelCourtRepository) {
        this.padelCourtRepository = padelCourtRepository;
    }

    @Transactional
    @Override
    public PadelCourt save(PadelCourt padelCourt) {
        return padelCourtRepository.save(padelCourt);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        padelCourtRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PadelCourt create(PadelCourt padelCourt) {
        if (padelCourt.getName() == null || padelCourt.getType() == null) {
            throw new IllegalArgumentException("Name and type are required fields.");
        }

        if (padelCourtRepository.existsByName(padelCourt.getName())) {
            throw new CourtAlreadyExistsException("A court with the name " + padelCourt.getName() + " already exists.");
        }

        return padelCourtRepository.save(padelCourt);
    }

    @Transactional
    @Override
    public PadelCourt updateById(int id, PadelCourt updatedCourt) {
        PadelCourt existingCourt = padelCourtRepository.findById(id);
        if (!existingCourt.getName().equals(updatedCourt.getName())
                && padelCourtRepository.existsByName(updatedCourt.getName())) {
            throw new CourtAlreadyExistsException("A court with the name " + updatedCourt.getName() + " already exists.");
        }
        existingCourt.setName(updatedCourt.getName());
        existingCourt.setType(updatedCourt.getType());

        return padelCourtRepository.save(existingCourt);
    }
}
