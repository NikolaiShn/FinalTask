package com.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "knowledge_directories")
public class KnowledgeDirectory {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "knowledgeDirectory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theme> themes;
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
