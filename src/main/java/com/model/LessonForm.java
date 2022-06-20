package com.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "lessons_form")
public class LessonForm {
    @Id
    private Long id;
    @Column(name = "form_name")
    private String formName;
}
