package com.model;

import java.util.List;

public class Profile {
    private Long id;
    private Long userId;
    private String name;
    private String lastName;
    private Schedule schedule;
    private List<Long> knowledgeDirectoryIds;
    private List<Long> coursesIds;
}
