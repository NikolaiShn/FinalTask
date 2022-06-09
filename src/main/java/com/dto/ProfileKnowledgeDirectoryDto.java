package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ProfileKnowledgeDirectoryDto {
    @JsonIgnore
    private Long id;
    private String name;
    private List<ThemeDto> themes;
    private List<SectionDto> sections;
}
