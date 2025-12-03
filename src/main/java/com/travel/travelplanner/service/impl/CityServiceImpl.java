package com.travel.travelplanner.service.impl;

import com.travel.travelplanner.dto.CityDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Country;
import com.travel.travelplanner.mapper.CityMapper;
import com.travel.travelplanner.repository.CityRepository;
import com.travel.travelplanner.repository.CountryRepository;
import com.travel.travelplanner.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDto> getAll() {
        return cityMapper.toDtoList(cityRepository.findAll());
    }

    @Override
    public CityDto getById(Long id) {
        return cityMapper.toDto(cityRepository.findById(id).orElse(null));
    }

    @Override
    public CityDto create(CityDto dto) {

        Country country = countryRepository.findById(dto.getCountryId())
                .orElse(null);

        City city = City.builder()
                .name(dto.getName())
                .country(country)
                .build();

        cityRepository.save(city);

        return cityMapper.toDto(city);
    }

    @Override
    public CityDto update(Long id, CityDto dto) {
        City city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            return null;
        }

        if (dto.getCountryId() != null) {
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElse(null);
            city.setCountry(country);
        }

        city.setName(dto.getName());

        City saved = cityRepository.save(city);

        return cityMapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!cityRepository.existsById(id)) {
            return false;
        }

        cityRepository.deleteById(id);
        return true;
    }
}