package com.web.services;

import com.dto.CourseDto;
import com.dto.LessonDto;
import com.dto.mappers.CourseMapper;
import com.dto.mappers.LessonMapper;
import com.exceptions.*;
import com.model.Course;
import com.model.Lesson;
import com.model.LessonForm;
import com.model.User;
import com.web.controllers.AuthenticationFacade;
import com.web.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonFormRepository lessonFormRepository;

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
    public List<LessonDto> getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessons);
    }

    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        return CourseMapper.INSTANCE.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)));
    }

    //сортировка по возврастанию
    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        return CourseMapper.INSTANCE.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)).stream().sorted(Comparator.comparing(Course::getCost)).toList());
    }

    //сортировка по возврастанию
    @Transactional
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() throws NotAuthenticatedException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        return LessonMapper.INSTANCE.lessonsToLessonDtos(lessons.stream().sorted(Comparator.comparing(Lesson::getCost)).toList());
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

    //работает только на аутентифицированных пользователей
    @Transactional
    public boolean subscribeToCourse(String courseName) throws NotFoundException, NotAuthenticatedException, CourseExistException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        Course course = courseRepository.findByCourseName(courseName);
        if(course == null) {
            throw new NotFoundException("Курса с таким именем не существует");
        } else {
            User user = userRepository.findByLogin(login);
            List<Course> userCourses = user.getCourses();
            if(userCourses.contains(course)) {
                throw new CourseExistException("На данный курс вы уже подписаны");
            } else {
                userCourses.add(course);
                user.setCourses(userCourses);
                userRepository.save(user);
            }
            return true;
        }
    }

    //работает только на аутентифицированных пользователей и сразу подписывает на все занятия
    @Transactional
    public boolean subscribeToLesson(String description) throws NotFoundException, NotAuthenticatedException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        Lesson lesson = lessonRepository.findByDescription(description);
        if(lesson == null) {
            throw new NotFoundException("Такого занятия не существует");
        }
        Course course = courseRepository.findByCourseName(lesson.getCourse().getCourseName());
        User user = userRepository.findByLogin(login);
        List<Course> userCourses = user.getCourses();
            if(userCourses.contains(course)) {
                throw new NotFoundException("Такое занятие уже есть");
            } else {
                userCourses.add(course);
                user.setCourses(userCourses);
                userRepository.save(user);
            }
        return true;
    }

    @Transactional
    public boolean createCourse(String courseName, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException, NotAuthenticatedException, CourseExistException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        if(endDate.isBefore(startDate)) {
            throw new InvalidDateException("Дата не корректна");
        } else {
            Course newCourse = new Course(courseName, cost, startDate, endDate);
            User user = userRepository.findByLogin(login);
            List<Course> courses = user.getCourses();
            for(Course course : courses) {
                if(course.getCourseName().equals(newCourse.getCourseName())) {
                    throw new CourseExistException("Такой курс присутствует");
                }
            }
                courses.add(newCourse);
                user.setCourses(courses);
                userRepository.save(user);
                return true;
        }
    }

    //имя курса будет соответствовать имени занятия, в занятии пустое время
    //форма занятия по дефолту индивидуальное
    @Transactional
    public boolean createLesson(String lessonName, String description, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException, NotAuthenticatedException, CourseExistException, LessonExistException {
        String login = authenticationFacade.getAuthentication().getName();
        if(login == null) {
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        if(endDate.isBefore(startDate)) {
            throw new InvalidDateException("Дата не корректна");
        } else {
            Lesson lesson = lessonRepository.findByLessonName(lessonName);
            if(lesson != null) {
                throw new LessonExistException("такое занятие уже существует");
            }
            Course newCourse = new Course(lessonName, cost, startDate, endDate);
            courseRepository.save(newCourse);
            List<Lesson> lessons = new ArrayList<>();
            Lesson newLesson = new Lesson();
            LessonForm lessonForm = lessonFormRepository.findByFormName("индивидуальное");
            newLesson.setLessonName(lessonName);
            newLesson.setDescription(description);
            newLesson.setCost(cost);
            newLesson.setLessonForm(lessonForm);
            newLesson.setCourse(newCourse);
            lessons.add(newLesson);
            lessonRepository.save(newLesson);
            User user = userRepository.findByLogin(login);
            List<Course> courses = user.getCourses();
            courses.add(newCourse);
            user.setCourses(courses);
            userRepository.save(user);
            return true;
        }
    }
}
