package com.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "knowledge_directories")
public class KnowledgeDirectory {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "knowledgeDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theme> themes;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "knowledgeDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;

    public void addTheme(Theme theme) {
        themes.add(theme);
        theme.setKnowledgeDirectory(this);
    }

    public void removeTheme(Theme theme) {
        themes.remove(theme);
        theme.setKnowledgeDirectory(null);
    }

    public void addSection(Section section) {
        sections.add(section);
        section.setKnowledgeDirectory(this);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.setKnowledgeDirectory(null);
    }
}
