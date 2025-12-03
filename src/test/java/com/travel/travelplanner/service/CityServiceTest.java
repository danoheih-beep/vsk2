package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.CityDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Test
    void getAllTest() {

        List<CityDto> list = cityService.getAll();

        assertNotNull(list);
        assertTrue(list.size() > 0);

        for (CityDto dto : list) {
            assertNotNull(dto.getId());
            assertNotNull(dto.getName());
            assertNotNull(dto.getCountryId());
        }
    }

    @Test
    void getByIdTest() {

        Long id = cityService.getAll().get(0).getId();

        CityDto dto = cityService.getById(id);

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertNotNull(dto.getCountryId());

        assertNull(cityService.getById(-1L));
    }

    @Test
    void createTest() {

        CityDto dto = new CityDto();
        dto.setName("City Test");
        dto.setCountryId(1L);

        CityDto created = cityService.create(dto);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("City Test", created.getName());
        assertEquals(1L, created.getCountryId());

        CityDto found = cityService.getById(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void updateTest() {

        CityDto dto = new CityDto();
        dto.setName("Original");
        dto.setCountryId(1L);

        CityDto created = cityService.create(dto);

        CityDto updateDto = new CityDto();
        updateDto.setName("Updated Name");
        updateDto.setCountryId(1L);

        CityDto updated = cityService.update(created.getId(), updateDto);

        assertNotNull(updated);
        assertEquals("Updated Name", updated.getName());

        CityDto found = cityService.getById(created.getId());

        assertNotNull(found);
        assertEquals("Updated Name", found.getName());
    }

    @Test
    void deleteTest() {

        CityDto dto = new CityDto();
        dto.setName("Delete City");
        dto.setCountryId(1L);

        CityDto created = cityService.create(dto);

        boolean deleted = cityService.delete(created.getId());
        assertTrue(deleted);

        assertNull(cityService.getById(created.getId()));
    }
}