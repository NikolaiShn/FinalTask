package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
public class ThemeDto {
    @JsonIgnore
    private Long id;
    private String themeName;
    @JsonIgnore
    @ToString.Exclude
    private KnowledgeDirectoryDto knowledgeDirectory;
}
