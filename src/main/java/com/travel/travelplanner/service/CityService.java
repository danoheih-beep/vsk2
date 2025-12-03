package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> getAll();

    CityDto getById(Long id);

    CityDto create(CityDto dto);

    CityDto update(Long id, CityDto dto);

    boolean delete(Long id);
}