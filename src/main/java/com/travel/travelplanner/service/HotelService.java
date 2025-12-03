package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.HotelDto;

import java.util.List;

public interface HotelService {

    List<HotelDto> getAll();

    HotelDto getById(Long id);

    HotelDto create(HotelDto dto);

    HotelDto update(Long id, HotelDto dto);

    boolean delete(Long id);
}