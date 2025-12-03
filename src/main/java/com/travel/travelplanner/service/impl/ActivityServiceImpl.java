package com.travel.travelplanner.service.impl;

import com.travel.travelplanner.dto.ActivityDto;
import com.travel.travelplanner.entity.Activity;
import com.travel.travelplanner.mapper.ActivityMapper;
import com.travel.travelplanner.repository.ActivityRepository;
import com.travel.travelplanner.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;
    private final ActivityMapper mapper;

    @Override
    public List<ActivityDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public ActivityDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public ActivityDto create(ActivityDto dto) {
        Activity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public ActivityDto update(Long id, ActivityDto dto) {
        Activity entity = repository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }

        // обновляем ТОЛЬКО реальные поля
        entity.setName(dto.getName());

        Activity saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}