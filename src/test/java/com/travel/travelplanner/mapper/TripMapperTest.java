package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.TripDto;
import com.travel.travelplanner.entity.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mapstruct.factory.Mappers;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TripMapperTest {

    private TripMapper tripMapper = Mappers.getMapper(TripMapper.class);

    @Test
    void convertEntityToDtoTest() {

        Trip trip = Trip.builder()
                .id(1L)
                .title("Winter Trip")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(2))
                .description("desc")
                .build();

        TripDto dto = tripMapper.toDto(trip);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(trip.getId(), dto.getId());
        Assertions.assertEquals(trip.getTitle(), dto.getTitle());
    }

    @Test
    void convertDtoToEntityTest() {

        TripDto dto = TripDto.builder()
                .id(10L)
                .title("Summer Trip")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .description("desc")
                .build();

        Trip entity = tripMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getTitle(), entity.getTitle());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Trip> list = new ArrayList<>();
        list.add(Trip.builder().id(1L).title("Trip1").build());
        list.add(Trip.builder().id(2L).title("Trip2").build());

        List<TripDto> dtoList = tripMapper.toDtoList(list);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
    }
}