package com.dto;

import com.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
        private Long id;
        private String login;
        private String password;
        private String name;
        private String lastName;
        private Role role;
        private List<KnowledgeDirectoryDto> knowledgeDirectories = new ArrayList<>();
        private List<CourseDto> courses = new ArrayList<>();
}
