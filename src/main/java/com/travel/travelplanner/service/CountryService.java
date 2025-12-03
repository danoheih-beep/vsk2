package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> getAll();

    CountryDto getById(Long id);

    CountryDto create(CountryDto dto);

    CountryDto update(Long id, CountryDto dto);

    boolean delete(Long id);
}