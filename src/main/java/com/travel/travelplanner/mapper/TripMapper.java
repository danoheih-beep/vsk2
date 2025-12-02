package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.TripDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "cityIds", source = "cities")
    TripDto toDto(Trip trip);

    @Mapping(target = "cities", ignore = true)
    Trip toEntity(TripDto dto);

    List<TripDto> toDtoList(List<Trip> trips);

    default List<Long> mapCitiesToIds(List<City> cities) {
        return cities == null ? null :
                cities.stream().map(City::getId).toList();
    }
}