package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class KnowledgeDirectoryDto {
    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private List<ThemeDto> themes;
    @JsonIgnore
    private List<SectionDto> sections;
}
