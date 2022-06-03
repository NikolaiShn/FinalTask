package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;


@Data
public class SectionDto {
    @JsonIgnore
    private Long id;
    private String sectionName;
    @JsonIgnore
    @ToString.Exclude
    private KnowledgeDirectoryDto knowledgeDirectory;
}
