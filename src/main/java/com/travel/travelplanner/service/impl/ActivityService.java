package com.travel.travelplanner.service;

import com.travel.travelplanner.dto.ActivityDto;

import java.util.List;

public interface ActivityService {

    List<ActivityDto> getAll();

    ActivityDto getById(Long id);

    ActivityDto create(ActivityDto dto);

    ActivityDto update(Long id, ActivityDto dto);

    boolean delete(Long id);
}