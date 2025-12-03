package com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.ActivityDto;
import com.travel.travelplanner.entity.Activity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.ArrayList;
import java.util.List;

public class ActivityMapperTest {

    private ActivityMapper activityMapper = Mappers.getMapper(ActivityMapper.class);

    @Test
    void convertEntityToDtoTest() {
        Activity activity = Activity.builder()
                .id(1L)
                .name("Hiking")
                .build();

        ActivityDto dto = activityMapper.toDto(activity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());

        Assertions.assertEquals(activity.getId(), dto.getId());
        Assertions.assertEquals(activity.getName(), dto.getName());
    }
    @Test
    void convertDtoToEntityTest() {
        ActivityDto dto = ActivityDto.builder()
                .id(5L)
                .name("Swimming")
                .build();

        Activity entity = activityMapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());

        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void convertEntityListToDtoListTest() {

        List<Activity> list = new ArrayList<>();
        list.add(Activity.builder().id(1L).name("Hiking").build());
        list.add(Activity.builder().id(2L).name("Skiing").build());

        List<ActivityDto> dtoList = activityMapper.toDtoList(list);

        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());

        for (int i = 0; i < list.size(); i++) {
            Activity a = list.get(i);
            ActivityDto dto = dtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertEquals(a.getId(), dto.getId());
            Assertions.assertEquals(a.getName(), dto.getName());
        }

    }
}