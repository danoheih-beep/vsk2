package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.TripDto;

import java.util.List;

public interface TripService {

    List<TripDto> getAll();

    TripDto getById(Long id);

    TripDto create(TripDto dto);

    TripDto update(Long id, TripDto dto);

    boolean delete(Long id);
}