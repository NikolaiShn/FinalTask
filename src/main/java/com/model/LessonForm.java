package com.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity(name = "lessons_form")
public class LessonForm implements Serializable {
    @Id
    private Long id;
    @Column(name = "form_name")
    private String formName;
}
