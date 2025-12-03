package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.HotelDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Hotel;
import com.travel.travelplanner.entity.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class HotelMapperTest {

    private HotelMapper hotelMapper = Mappers.getMapper(HotelMapper.class);


    @Test
    void convertEntityToDtoTest() {

        City city = City.builder().id(2L).name("Astana").build();
        Trip trip = Trip.builder().id(3L).title("Trip").build();

        Hotel hotel = Hotel.builder()
                .id(1L)
                .name("Ritz")
                .stars(5)
                .pricePerNight(50000)
                .city(city)
                .trip(trip)
                .build();

        HotelDto dto = hotelMapper.toDto(hotel);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(hotel.getId(), dto.getId());
        Assertions.assertEquals(hotel.getName(), dto.getName());
        Assertions.assertEquals(hotel.getStars(), dto.getStars());
        Assertions.assertEquals(hotel.getPricePerNight(), dto.getPricePerNight());
        Assertions.assertEquals(2L, dto.getCityId());
        Assertions.assertEquals(3L, dto.getTripId());
    }

    @Test
    void convertDtoToEntityTest() {

        HotelDto dto = HotelDto.builder()
                .id(15L)
                .name("Movenpick")
                .stars(4)
                .pricePerNight(30000)
                .cityId(1L)
                .tripId(1L)
                .build();

        Hotel hotel = hotelMapper.toEntity(dto);

        Assertions.assertNotNull(hotel);
        Assertions.assertEquals(dto.getId(), hotel.getId());
        Assertions.assertEquals(dto.getName(), hotel.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Hotel> list = new ArrayList<>();
        list.add(Hotel.builder().id(1L).name("Ritz").build());
        list.add(Hotel.builder().id(2L).name("Plaza").build());

        List<HotelDto> dtoList = hotelMapper.toDtoList(list);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
    }
}