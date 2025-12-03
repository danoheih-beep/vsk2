package com.travel.travelplanner.service;
import com.travel.travelplanner.dto.TripDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    void getAllTest() {

        List<TripDto> tripDtos = tripService.getAll();
        Assertions.assertNotNull(tripDtos);
        Assertions.assertNotEquals(0, tripDtos.size());

        for (TripDto dto : tripDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getStartDate());
            Assertions.assertNotNull(dto.getEndDate());
        }
    }

    @Test
    void getByIdTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(tripService.getAll().size());

        Long someId = tripService.getAll().get(randomIndex).getId();
        TripDto dto = tripService.getById(someId);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getTitle());
        Assertions.assertNotNull(dto.getStartDate());
        Assertions.assertNotNull(dto.getEndDate());

        TripDto notFound = tripService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void createTest() {

        TripDto dto = new TripDto();
        dto.setTitle("Test Trip");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusDays(1));
        dto.setDescription("Test description");

        TripDto created = tripService.create(dto);

        Assertions.assertNotNull(created);

        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getTitle(), created.getTitle());
        Assertions.assertEquals(dto.getStartDate(), created.getStartDate());
        Assertions.assertEquals(dto.getEndDate(), created.getEndDate());

        TripDto found = tripService.getById(created.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getId(), found.getId());
        Assertions.assertEquals(created.getTitle(), found.getTitle());
    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(tripService.getAll().size());

        Long someId = tripService.getAll().get(randomIndex).getId();

        TripDto dto = new TripDto();
        dto.setTitle("Updated Trip");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusDays(3));
        dto.setDescription("Updated description");

        TripDto beforeUpdate = tripService.update(someId, dto);

        Assertions.assertNotNull(beforeUpdate);
        Assertions.assertEquals(dto.getTitle(), beforeUpdate.getTitle());

        TripDto afterUpdate = tripService.getById(someId);

        Assertions.assertNotNull(afterUpdate);
        Assertions.assertEquals(beforeUpdate.getId(), afterUpdate.getId());
        Assertions.assertEquals(beforeUpdate.getTitle(), afterUpdate.getTitle());
    }

    @Test
    void deleteTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(tripService.getAll().size());

        Long someId = tripService.getAll().get(randomIndex).getId();

        Assertions.assertTrue(tripService.delete(someId));

        TripDto afterDelete = tripService.getById(someId);
        Assertions.assertNull(afterDelete);
    }
}