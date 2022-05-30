package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sections")
public class Section {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "section_name")
    private String sectionName;
    @ManyToOne
    private KnowledgeDirectory knowledgeDirectory;
}
