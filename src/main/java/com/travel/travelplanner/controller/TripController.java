package com.travel.travelplanner.controller;

import com.travel.travelplanner.dto.TripDto;
import com.travel.travelplanner.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity<List<TripDto>> getAll() {
        return ResponseEntity.ok(tripService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TripDto> create(@RequestBody TripDto dto) {
        return ResponseEntity.ok(tripService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> update(@PathVariable Long id, @RequestBody TripDto dto) {
        return ResponseEntity.ok(tripService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tripService.delete(id);
        return ResponseEntity.ok().build();
    }
}
