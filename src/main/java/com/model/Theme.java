package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "theme_name")
    private String themeName;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "knowledge_directory_id")
    private KnowledgeDirectory knowledgeDirectory;
}
