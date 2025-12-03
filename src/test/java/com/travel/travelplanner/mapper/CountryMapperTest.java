package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.CountryDto;
import com.travel.travelplanner.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mapstruct.factory.Mappers;


import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class CountryMapperTest {

    private CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);

    @Test
    void convertEntityToDtoTest() {
        Country country = Country.builder()
                .id(1L)
                .name("Kazakhstan")
                .build();

        CountryDto dto = countryMapper.toDto(country);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(country.getId(), dto.getId());
        Assertions.assertEquals(country.getName(), dto.getName());
    }

    @Test
    void convertDtoToEntityTest() {
        CountryDto dto = CountryDto.builder()
                .id(4L)
                .name("Uzbekistan")
                .build();

        Country entity = countryMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Country> list = new ArrayList<>();
        list.add(Country.builder().id(1L).name("Kazakhstan").build());
        list.add(Country.builder().id(2L).name("Kyrgyzstan").build());

        List<CountryDto> dtoList = countryMapper.toDtoList(list);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());
    }
}