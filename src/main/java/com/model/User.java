package com.model;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "users_ibfk_1"))
    private Role role;
    @Column
    private Double award = null;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "users_knowledge_directories", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "knowledge_directory_id")})
    private List<KnowledgeDirectory> knowledgeDirectories = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "users_courses", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses = new ArrayList<>();
}
