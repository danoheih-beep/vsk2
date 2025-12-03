package com.travel.travelplanner.service.impl;

import com.travel.travelplanner.dto.HotelDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Hotel;
import com.travel.travelplanner.entity.Trip;
import com.travel.travelplanner.mapper.HotelMapper;
import com.travel.travelplanner.repository.CityRepository;
import com.travel.travelplanner.repository.HotelRepository;
import com.travel.travelplanner.repository.TripRepository;
import com.travel.travelplanner.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final TripRepository tripRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDto> getAll() {
        return hotelMapper.toDtoList(hotelRepository.findAll());
    }

    @Override
    public HotelDto getById(Long id) {
        return hotelMapper.toDto(hotelRepository.findById(id).orElse(null));
    }

    @Override
    public HotelDto create(HotelDto dto) {

        City city = cityRepository.findById(dto.getCityId()).orElse(null);
        Trip trip = tripRepository.findById(dto.getTripId()).orElse(null);

        Hotel hotel = Hotel.builder()
                .name(dto.getName())
                .stars(dto.getStars())
                .pricePerNight(dto.getPricePerNight())
                .city(city)
                .trip(trip)
                .build();

        hotelRepository.save(hotel);

        return hotelMapper.toDto(hotel);
    }

    @Override
    public HotelDto update(Long id, HotelDto dto) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (hotel == null) {
            return null;
        }

        hotel.setName(dto.getName());
        hotel.setStars(dto.getStars());
        hotel.setPricePerNight(dto.getPricePerNight());

        if (dto.getCityId() != null) {
            City city = cityRepository.findById(dto.getCityId()).orElse(null);
            hotel.setCity(city);
        }

        // обновление trip
        if (dto.getTripId() != null) {
            Trip trip = tripRepository.findById(dto.getTripId()).orElse(null);
            hotel.setTrip(trip);
        }

        Hotel saved = hotelRepository.save(hotel);

        return hotelMapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!hotelRepository.existsById(id)) {
            return false;
        }

        hotelRepository.deleteById(id);
        return true;
    }
}