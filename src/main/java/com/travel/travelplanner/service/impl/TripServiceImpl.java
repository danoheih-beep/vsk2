package com.travel.travelplanner.service.impl;

import com.travel.travelplanner.dto.TripDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Trip;
import com.travel.travelplanner.mapper.TripMapper;
import com.travel.travelplanner.repository.CityRepository;
import com.travel.travelplanner.repository.TripRepository;
import com.travel.travelplanner.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;
    private final TripMapper tripMapper;

    @Override
    public List<TripDto> getAll() {
        return tripMapper.toDtoList(tripRepository.findAll());
    }

    @Override
    public TripDto getById(Long id) {
        return tripMapper.toDto(tripRepository.findById(id).orElse(null));
    }

    @Override
    public TripDto create(TripDto dto) {

        List<City> cities = new ArrayList<>();

        if (dto.getCityIds() != null) {
            cities = cityRepository.findAllById(dto.getCityIds());
        }

        Trip trip = Trip.builder()
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .description(dto.getDescription())
                .cities(cities)
                .build();

        tripRepository.save(trip);

        return tripMapper.toDto(trip);
    }

    @Override
    public TripDto update(Long id, TripDto dto) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip == null) {
            return null;
        }

        trip.setTitle(dto.getTitle());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());
        trip.setDescription(dto.getDescription());

        if (dto.getCityIds() != null) {
            List<City> cities = cityRepository.findAllById(dto.getCityIds());
            trip.setCities(cities);
        }

        Trip saved = tripRepository.save(trip);
        return tripMapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!tripRepository.existsById(id)) {
            return false;
        }

        tripRepository.deleteById(id);
        return true;
    }
}