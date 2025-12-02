package com.travel.travelplanner.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String name;
    private Long countryId;
}
