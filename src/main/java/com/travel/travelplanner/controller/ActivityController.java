package com.travel.travelplanner.controller;

import com.travel.travelplanner.dto.ActivityDto;
import com.travel.travelplanner.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<ActivityDto>> getAll() {
        return ResponseEntity.ok(activityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityDto> create(@RequestBody ActivityDto dto) {
        return ResponseEntity.ok(activityService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> update(@PathVariable Long id, @RequestBody ActivityDto dto) {
        return ResponseEntity.ok(activityService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
