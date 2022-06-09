package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProfileDto {
    private String name;
    private String lastName;
    private List<ProfileCourseDto> courseDtos;
    private List<ProfileKnowledgeDirectoryDto> knowledgeDirectoryDtos;
}
