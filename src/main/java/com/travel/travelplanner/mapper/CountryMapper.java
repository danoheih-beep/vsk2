package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.CountryDto;
import com.travel.travelplanner.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);

    Country toEntity(CountryDto dto);

    List<CountryDto> toDtoList(List<Country> list);
}
