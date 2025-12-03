package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.CityDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CityMapperTest {

    private CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    @Test
    void convertEntityToDtoTest() {

        Country country = Country.builder().id(1L).name("Kazakhstan").build();

        City city = City.builder()
                .id(10L)
                .name("Almaty")
                .country(country)
                .build();

        CityDto dto = cityMapper.toDto(city);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(city.getId(), dto.getId());
        Assertions.assertEquals(city.getName(), dto.getName());
        Assertions.assertEquals(1L, dto.getCountryId());
    }

    @Test
    void convertDtoToEntityTest() {

        CityDto dto = CityDto.builder()
                .id(20L)
                .name("Astana")
                .countryId(2L)
                .build();

        City entity = cityMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<City> list = new ArrayList<>();
        list.add(City.builder().id(1L).name("Almaty").build());
        list.add(City.builder().id(2L).name("Shymkent").build());

        List<CityDto> dtoList = cityMapper.toDtoList(list);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
    }
}