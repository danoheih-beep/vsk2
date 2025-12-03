package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.ActivityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @BeforeEach
    void setup() {
    }

    @Test
    void getAllTest() {
        List<ActivityDto> all = activityService.getAll();

        assertNotNull(all);
        assertTrue(all.size() > 0);

        for (ActivityDto dto : all) {
            assertNotNull(dto);
            assertNotNull(dto.getId());
            assertNotNull(dto.getName());
        }
    }

    @Test
    void getByIdTest() {
        Long id = activityService.getAll().get(0).getId();

        ActivityDto dto = activityService.getById(id);

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertNotNull(dto.getName());

        ActivityDto notFound = activityService.getById(-1L);
        assertNull(notFound);
    }

    @Test
    void createTest() {

        ActivityDto dto = new ActivityDto();
        dto.setName("Running");

        ActivityDto created = activityService.create(dto);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Running", created.getName());

        ActivityDto found = activityService.getById(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
        assertEquals("Running", found.getName());
    }

    @Test
    void updateTest() {

        Long id = activityService.getAll().get(0).getId();

        ActivityDto dto = new ActivityDto();
        dto.setName("Updated Activity");

        ActivityDto updated = activityService.update(id, dto);

        assertNotNull(updated);
        assertEquals("Updated Activity", updated.getName());

        ActivityDto found = activityService.getById(id);

        assertNotNull(found);
        assertEquals("Updated Activity", found.getName());
    }

    @Test
    void deleteTest() {

        Long id = activityService.getAll().get(0).getId();

        boolean deleted = activityService.delete(id);
        assertTrue(deleted);

        ActivityDto afterDelete = activityService.getById(id);
        assertNull(afterDelete);
    }
}