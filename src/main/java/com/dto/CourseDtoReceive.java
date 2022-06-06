package com.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDtoReceive {
    private String courseName;
    private Double cost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
