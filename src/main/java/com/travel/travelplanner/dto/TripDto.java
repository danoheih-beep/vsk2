package com.travel.travelplanner.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    private List<Long> cityIds;
}
