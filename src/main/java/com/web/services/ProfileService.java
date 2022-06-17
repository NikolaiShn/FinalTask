package com.web.services;

import com.dto.ProfileCourseDto;
import com.dto.ProfileDto;
import com.dto.ProfileKnowledgeDirectoryDto;
import com.dto.mappers.ProfileCourseMapper;
import com.dto.mappers.ProfileKnowledgeDirectoryMapper;
import com.model.User;
import com.web.dao.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {

    private static final Logger profileServiceLogger = LogManager.getLogger(ProfileService.class);

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileCourseMapper profileCourseMapper;
    @Autowired
    private ProfileKnowledgeDirectoryMapper profileKnowledgeDirectoryMapper;

    @Transactional
    public ProfileDto getProfile() {
        profileServiceLogger.info("start getProfile");
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        List<ProfileCourseDto> profileCourseDtos = profileCourseMapper.coursesToProfileCourseDtos(userRepository.findCoursesByUserFetch(user));
        List<ProfileKnowledgeDirectoryDto> profileKnowledgeDirectoryDtos = profileKnowledgeDirectoryMapper.knowledgeDirectoriesToProfileKnowledgeDirectoriesDto(userRepository.findKnowledgeDirectoriesByUserFetch(user));
        profileServiceLogger.info("end getProfile");
        return new ProfileDto(user.getName(), user.getLastName(), profileCourseDtos, profileKnowledgeDirectoryDtos);
    }

    @Transactional
    public boolean changeProfileUserName(String newName) {
        profileServiceLogger.info("start changeProfileUserName");
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        user.setName(newName);
        userRepository.save(user);
        profileServiceLogger.info("end changeProfileUserName");
        return true;
    }

    @Transactional
    public boolean changeProfileUserLastName(String newLastName) {
        profileServiceLogger.info("start changeProfileUserLastName");
        String login = authenticationFacade.getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        user.setLastName(newLastName);
        userRepository.save(user);
        profileServiceLogger.info("end changeProfileUserLastName");
        return true;
    }
}
