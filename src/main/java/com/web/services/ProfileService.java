package com.web.services;

import com.dto.ProfileCourseDto;
import com.dto.ProfileDto;
import com.dto.ProfileKnowledgeDirectoryDto;
import com.dto.mappers.ProfileCourseMapper;
import com.dto.mappers.ProfileKnowledgeDirectoryMapper;
import com.model.User;
import com.web.controllers.AuthenticationFacade;
import com.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;

    //возможно сделать на 2 запроса меньше
    @Transactional
    public ProfileDto getProfile() {
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        List<ProfileCourseDto> profileCourseDtos = ProfileCourseMapper.INSTANCE.coursesToProfileCourseDtos(userRepository.findCoursesByUserFetch(user));
        List<ProfileKnowledgeDirectoryDto> profileKnowledgeDirectoryDtos = ProfileKnowledgeDirectoryMapper.INSTANCE.knowledgeDirectoriesToProfileKnowledgeDirectoriesDto(userRepository.findKnowledgeDirectoriesByUserFetch(user));
        return new ProfileDto(user.getName(), user.getLastName(), profileCourseDtos, profileKnowledgeDirectoryDtos);
    }

    @Transactional
    public boolean changeProfileUserName(String newName) {
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        user.setName(newName);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean changeProfileUserLastName(String newLastName) {
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        user.setName(newLastName);
        userRepository.save(user);
        return true;
    }


}
