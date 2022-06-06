package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sections")
public class Section implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "section_name")
    private String sectionName;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "knowledge_directory_id")
    private KnowledgeDirectory knowledgeDirectory;
}
