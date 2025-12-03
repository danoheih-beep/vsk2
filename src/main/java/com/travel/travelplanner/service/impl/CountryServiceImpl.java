package com.travel.travelplanner.service.impl;

import com.travel.travelplanner.dto.CountryDto;
import com.travel.travelplanner.entity.Country;
import com.travel.travelplanner.mapper.CountryMapper;
import com.travel.travelplanner.repository.CountryRepository;
import com.travel.travelplanner.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getAll() {
        return countryMapper.toDtoList(countryRepository.findAll());
    }

    @Override
    public CountryDto getById(Long id) {
        return countryMapper.toDto(countryRepository.findById(id).orElse(null));
    }

    @Override
    public CountryDto create(CountryDto dto) {
        Country entity = countryMapper.toEntity(dto);
        countryRepository.save(entity);
        return countryMapper.toDto(entity);
    }

    @Override
    public CountryDto update(Long id, CountryDto dto) {
        Country entity = countryRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }

        entity.setName(dto.getName());

        Country saved = countryRepository.save(entity);
        return countryMapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!countryRepository.existsById(id)) {
            return false;
        }

        countryRepository.deleteById(id);
        return true;
    }
}