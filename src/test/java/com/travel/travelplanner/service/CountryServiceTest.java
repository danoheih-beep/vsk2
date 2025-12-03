package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.CountryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllTest() {

        List<CountryDto> countryDtos = countryService.getAll();
        Assertions.assertNotNull(countryDtos);
        Assertions.assertNotEquals(0, countryDtos.size());

        for (CountryDto dto : countryDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
        }
    }

    @Test
    void getByIdTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(countryService.getAll().size());

        Long someId = countryService.getAll().get(randomIndex).getId();
        CountryDto dto = countryService.getById(someId);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());

        CountryDto notFound = countryService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void createTest() {

        CountryDto dto = new CountryDto();
        dto.setName("TestCountry");

        CountryDto created = countryService.create(dto);

        Assertions.assertNotNull(created);

        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getName());

        Assertions.assertEquals(dto.getName(), created.getName());

        CountryDto found = countryService.getById(created.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getId(), found.getId());
        Assertions.assertEquals(created.getName(), found.getName());
    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAll().size());

        Long someId = countryService.getAll().get(randomIndex).getId();

        CountryDto dto = new CountryDto();
        dto.setName("UpdatedCountry");

        CountryDto updated = countryService.update(someId, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(dto.getName(), updated.getName());

        CountryDto afterUpdate = countryService.getById(someId);

        Assertions.assertNotNull(afterUpdate);
        Assertions.assertEquals(updated.getId(), afterUpdate.getId());
        Assertions.assertEquals(updated.getName(), afterUpdate.getName());
    }

    @Test
    void deleteTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAll().size());

        Long someId = countryService.getAll().get(randomIndex).getId();

        Assertions.assertTrue(countryService.delete(someId));

        CountryDto afterDelete = countryService.getById(someId);
        Assertions.assertNull(afterDelete);
    }
}