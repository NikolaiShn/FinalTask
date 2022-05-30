package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "lessons_form")
public class LessonForm {
    @Id
    private Long id;
    @Column(name = "form_name")
    private String formName;
}
