package com.travel.travelplanner.controller;

import com.travel.travelplanner.dto.HotelDto;
import com.travel.travelplanner.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<HotelDto> create(@RequestBody HotelDto dto) {
        return ResponseEntity.ok(hotelService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable Long id, @RequestBody HotelDto dto) {
        return ResponseEntity.ok(hotelService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        hotelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
