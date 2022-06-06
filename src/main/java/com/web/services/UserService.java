package com.web.services;

import com.dto.CourseDto;
import com.dto.LessonDto;
import com.dto.mappers.CourseMapper;
import com.dto.mappers.LessonMapper;
import com.exceptions.NotFoundException;
import com.exceptions.UserExistException;
import com.model.Course;
import com.model.Lesson;
import com.model.User;
import com.web.controllers.AuthenticationFacade;
import com.web.dao.RoleRepository;
import com.web.dao.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User getUserByLogin(String login) throws NotFoundException {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundException("такого юзера не существует");
        }
    }

    @Transactional
    public List<LessonDto> getCurrentUserAccessibleLessons() {
        String login = authenticationFacade.getAuthentication().getName();
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessons);
    }

    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCourses() {
        String login = authenticationFacade.getAuthentication().getName();
        return CourseMapper.INSTANCE.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)));
    }

    //сортировка по возврастанию
    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() {
        String login = authenticationFacade.getAuthentication().getName();
        userRepository.findByLogin(login).getCourses();
        return CourseMapper.INSTANCE.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)).stream().sorted(Comparator.comparing(Course::getCost)).toList());
    }

    //сортировка по возврастанию
    @Transactional
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() {
        String login = authenticationFacade.getAuthentication().getName();
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessons.stream().sorted(Comparator.comparing(Lesson::getCost)).toList());
    }

    @Transactional
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByLogin(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                authorities);
    }

    @Transactional
    public boolean registerUser(String username, String password, String role, String name, String lastName) throws UserExistException {
        if(userRepository.findByLogin(username) != null) {
            throw new UserExistException("такой пользователь существует");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setLogin(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user.setLastName(lastName);
            String fullNameRole = "ROLE_" + role;
            user.setRole(roleRepository.findByRole(fullNameRole));
            userRepository.save(user);
            return true;
        }
    }
}
