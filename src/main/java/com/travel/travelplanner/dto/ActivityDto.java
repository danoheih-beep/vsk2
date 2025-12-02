package com.travel.travelplanner.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDto {
    private Long id;

    private String name;
    private String category;
    private double price;

    private Long tripId;
    private Long cityId;
}
