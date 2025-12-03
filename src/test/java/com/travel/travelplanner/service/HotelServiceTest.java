package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.HotelDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Test
    void getAllTest() {

        List<HotelDto> hotelDtos = hotelService.getAll();
        Assertions.assertNotNull(hotelDtos);
        Assertions.assertNotEquals(0, hotelDtos.size());

        for (HotelDto dto : hotelDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getStars());
            Assertions.assertNotNull(dto.getPricePerNight());
            Assertions.assertNotNull(dto.getCityId());
            Assertions.assertNotNull(dto.getTripId());
        }
    }

    @Test
    void getByIdTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(hotelService.getAll().size());

        Long someId = hotelService.getAll().get(randomIndex).getId();
        HotelDto dto = hotelService.getById(someId);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertNotNull(dto.getStars());
        Assertions.assertNotNull(dto.getPricePerNight());
        Assertions.assertNotNull(dto.getCityId());
        Assertions.assertNotNull(dto.getTripId());

        HotelDto notFound = hotelService.getById(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void createTest() {

        HotelDto dto = new HotelDto();
        dto.setName("Test Hotel");
        dto.setStars(5);
        dto.setPricePerNight(35000);
        dto.setCityId(1L);
        dto.setTripId(1L);

        HotelDto created = hotelService.create(dto);

        Assertions.assertNotNull(created);

        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals(dto.getName(), created.getName());
        Assertions.assertEquals(dto.getStars(), created.getStars());
        Assertions.assertEquals(dto.getPricePerNight(), created.getPricePerNight());
        Assertions.assertEquals(dto.getCityId(), created.getCityId());
        Assertions.assertEquals(dto.getTripId(), created.getTripId());

        HotelDto found = hotelService.getById(created.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(created.getName(), found.getName());
    }

    @Test
    void updateTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(hotelService.getAll().size());

        Long someId = hotelService.getAll().get(randomIndex).getId();

        HotelDto dto = new HotelDto();
        dto.setName("Updated Hotel");
        dto.setStars(4);
        dto.setPricePerNight(25000);
        dto.setCityId(1L);
        dto.setTripId(1L);

        HotelDto beforeUpdate = hotelService.update(someId, dto);

        Assertions.assertNotNull(beforeUpdate);
        Assertions.assertEquals(dto.getName(), beforeUpdate.getName());
        Assertions.assertEquals(dto.getStars(), beforeUpdate.getStars());
        Assertions.assertEquals(dto.getPricePerNight(), beforeUpdate.getPricePerNight());

        HotelDto afterUpdate = hotelService.getById(someId);

        Assertions.assertNotNull(afterUpdate);
        Assertions.assertEquals(beforeUpdate.getName(), afterUpdate.getName());
        Assertions.assertEquals(beforeUpdate.getStars(), afterUpdate.getStars());
        Assertions.assertEquals(beforeUpdate.getPricePerNight(), afterUpdate.getPricePerNight());
    }

    @Test
    void deleteTest() {

        Random random = new Random();
        int randomIndex = random.nextInt(hotelService.getAll().size());

        Long someId = hotelService.getAll().get(randomIndex).getId();

        Assertions.assertTrue(hotelService.delete(someId));

        HotelDto afterDelete = hotelService.getById(someId);
        Assertions.assertNull(afterDelete);
    }
}