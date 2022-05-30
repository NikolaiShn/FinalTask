package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "roles")
public class Role {

    @Id
    private Long id;
    @Column
    private String role;
}
