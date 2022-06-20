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
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "theme_name")
    private String themeName;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "knowledge_directory_id")
    private KnowledgeDirectory knowledgeDirectory;
}
