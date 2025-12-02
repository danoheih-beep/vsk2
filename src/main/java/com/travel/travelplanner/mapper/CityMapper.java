package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.CityDto;
import com.travel.travelplanner.entity.City;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(source = "country.id", target = "countryId")
    CityDto toDto(City city);

    @Mapping(source = "countryId", target = "country.id")
    City toEntity(CityDto dto);

    List<CityDto> toDtoList(List<City> list);
}