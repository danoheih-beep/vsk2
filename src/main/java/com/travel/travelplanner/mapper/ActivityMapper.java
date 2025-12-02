package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.ActivityDto;
import com.travel.travelplanner.entity.Activity;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Trip;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    @Mapping(source = "trip.id", target = "tripId")
    @Mapping(source = "city.id", target = "cityId")
    ActivityDto toDto(Activity activity);

    @Mapping(source = "tripId", target = "trip.id")
    @Mapping(source = "cityId", target = "city.id")
    Activity toEntity(ActivityDto dto);

    List<ActivityDto> toDtoList(List<Activity> list);
}