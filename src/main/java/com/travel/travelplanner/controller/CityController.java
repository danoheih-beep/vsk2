package com.travel.travelplanner.controller;

import com.travel.travelplanner.dto.CityDto;
import com.travel.travelplanner.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody CityDto dto) {
        return ResponseEntity.ok(cityService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@PathVariable Long id, @RequestBody CityDto dto) {
        return ResponseEntity.ok(cityService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
