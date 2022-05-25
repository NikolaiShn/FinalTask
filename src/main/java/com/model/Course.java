package com.model;

import java.util.List;

public class Course {
    private Integer id;
    private String courseName;
    private List<Long> lessonsId;
    //scheduleId
    private Schedule schedule;
    private List<Long> reviewIds;
    private Double cost;
}
