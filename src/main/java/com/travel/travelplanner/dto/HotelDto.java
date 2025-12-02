package com.travel.travelplanner.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {
    private Long id;
    private String name;
    private int stars;
    private double pricePerNight;

    private Long cityId;
    private Long tripId;
}
