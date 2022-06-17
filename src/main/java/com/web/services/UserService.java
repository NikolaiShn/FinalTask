package com.web.services;

import com.dto.CourseDto;
import com.dto.LessonDto;
import com.dto.ScheduleLessonDto;
import com.dto.mappers.CourseMapper;
import com.dto.mappers.LessonMapper;
import com.dto.mappers.ScheduleLessonMapper;
import com.exceptions.*;
import com.model.*;
import com.web.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private static final Logger userServiceLogger = LogManager.getLogger(UserService.class);

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
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private ScheduleLessonMapper scheduleLessonMapper;
//    @Autowired
//    private JavaMailSenderImpl mailSender;
//    @Autowired
//    private VelocityEngine velocityEngine;

    @Transactional
    public User getUserByLogin(String login) throws NotFoundException {
        userServiceLogger.info("start getUserByLogin login = " + login);
        User user = userRepository.findByLogin(login);
        if (user != null) {
            userServiceLogger.info("end getUserByLogin login = " + login);
            return user;
        } else {
            userServiceLogger.error("end getUserByLogin login = " + login);
            throw new NotFoundException("такого юзера не существует");
        }
    }

    @Transactional
    public List<LessonDto> getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        userServiceLogger.info("start getCurrentUserAccessibleLessons ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифиирован");
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        userServiceLogger.info("end getCurrentUserAccessibleLessons ");
        return lessonMapper.lessonsToLessonDtos(lessons);
    }

    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        userServiceLogger.info("start getCurrentUserAccessibleCourses ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифиирован");
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        userServiceLogger.info("end getCurrentUserAccessibleCourses ");
        return courseMapper.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)));
    }

    //сортировка по возврастанию
    @Transactional
    public List<CourseDto> getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        userServiceLogger.info("start getCurrentUserAccessibleCoursesSortByCost ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифиирован");
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        userServiceLogger.info("end getCurrentUserAccessibleCoursesSortByCost ");
        return courseMapper.coursesToCourseDtos(userRepository.findCoursesByUserFetch(userRepository.findByLogin(login)).stream().sorted(Comparator.comparing(Course::getCost)).toList());
    }

    //сортировка по возврастанию
    @Transactional
    public List<LessonDto> getCurrentUserAccessibleLessonsSortByCost() throws NotAuthenticatedException {
        userServiceLogger.info("start getCurrentUserAccessibleLessonsSortByCost ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифиирован");
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        List<Course> userCourses = userRepository.findCoursesByUserFetch(userRepository.findByLogin(login));
        List<Lesson> lessons = new ArrayList<>();
        for (Course course : userCourses) {
            lessons.addAll(userRepository.findLessonsByCourseFetch(course.getCourseName()));
        }
        userServiceLogger.info("end getCurrentUserAccessibleLessonsSortByCost ");
        return lessonMapper.lessonsToLessonDtos(lessons.stream().sorted(Comparator.comparing(Lesson::getCost)).toList());
    }

    @Transactional
    public boolean registerUser(String username, String password, String role, String name, String lastName) throws UserExistException, RoleNotExistException {
        userServiceLogger.info("start registerUser ");
        if (userRepository.findByLogin(username) != null) {
            userServiceLogger.error("такой пользователь существует");
            throw new UserExistException("такой пользователь существует");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setLogin(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user.setLastName(lastName);
            String fullNameRole = "ROLE_" + role;
            Role roleInBd = roleRepository.findByRole(fullNameRole);
            if(roleInBd == null) {
                throw new RoleNotExistException("Такой роли не существует");
            }
            user.setRole(roleInBd);
            userRepository.save(user);
//            try {
//                MimeMessagePreparator preparator = mimeMessage -> {
//                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//                    message.setTo("recipient_email");
//                    Template t = velocityEngine.getTemplate("templates/template.vm", "UTF-8");
//                    VelocityContext context = new VelocityContext();
//                    context.put("name", "World");
//                    StringWriter writer = new StringWriter();
//                    t.merge(context, writer);
//                    message.setText(writer.toString(), true);
//            };
//                mailSender.send(preparator);
//            } catch (ResourceNotFoundException |
//                    ParseErrorException e) {
//                System.out.println(e);
//            }
            userServiceLogger.info("end registerUser ");
            return true;
        }
    }

    //работает только на аутентифицированных пользователей
    @Transactional
    public boolean subscribeToCourse(String courseName) throws NotFoundException, NotAuthenticatedException, CourseExistException {
        userServiceLogger.info("start subscribeToCourse ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифиирован");
            throw new NotAuthenticatedException("Юзер не аутентифиирован");
        }
        Course course = courseRepository.findByCourseName(courseName);
        if (course == null) {
            userServiceLogger.error("Курса с таким именем не существует");
            throw new NotFoundException("Курса с таким именем не существует");
        } else {
            User user = userRepository.findByLogin(login);
            List<Course> userCourses = user.getCourses();
            if (userCourses.contains(course)) {
                userServiceLogger.error("На данный курс вы уже подписаны");
                throw new CourseExistException("На данный курс вы уже подписаны");
            } else {
                userCourses.add(course);
                user.setCourses(userCourses);
                userRepository.save(user);
            }
            userServiceLogger.info("end subscribeToCourse ");
            return true;
        }
    }

    //работает только на аутентифицированных пользователей и сразу подписывает на все занятия
    @Transactional
    public boolean subscribeToLesson(String description) throws NotFoundException, NotAuthenticatedException {
        userServiceLogger.info("start subscribeToLesson ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифицирован");
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        Lesson lesson = lessonRepository.findByDescription(description);
        if (lesson == null) {
            userServiceLogger.error("Такого занятия не существует");
            throw new NotFoundException("Такого занятия не существует");
        }
        Course course = courseRepository.findByCourseName(lesson.getCourse().getCourseName());
        User user = userRepository.findByLogin(login);
        List<Course> userCourses = user.getCourses();
            if (userCourses.contains(course)) {
                userServiceLogger.error("Такое занятие уже есть");
                throw new NotFoundException("Такое занятие уже есть");
            } else {
                userCourses.add(course);
                user.setCourses(userCourses);
                userRepository.save(user);
            }
        userServiceLogger.info("end subscribeToLesson ");
        return true;
    }

    @Transactional
    public boolean createCourse(String courseName, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException, NotAuthenticatedException, CourseExistException {
        userServiceLogger.info("start createCourse ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифицирован");
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        if (endDate.isBefore(startDate) || startDate.isBefore(LocalDateTime.now())) {
            userServiceLogger.error("Дата не корректна");
            throw new InvalidDateException("Дата не корректна");
        } else {
            Course newCourse = new Course(courseName, cost, startDate, endDate);
            User user = userRepository.findByLogin(login);
            List<Course> courses = user.getCourses();
            for (Course course : courses) {
                if (course.getCourseName().equals(newCourse.getCourseName())) {
                    userServiceLogger.error("Такой курс присутствует");
                    throw new CourseExistException("Такой курс присутствует");
                }
            }
                courses.add(newCourse);
                user.setCourses(courses);
                userRepository.save(user);
                userServiceLogger.info("end createCourse ");
                return true;
        }
    }

    //имя курса будет соответствовать имени занятия, в занятии пустое время
    //форма занятия по дефолту индивидуальное
    @Transactional
    public boolean createLesson(String lessonName, String courseName, String description, Double cost, LocalDateTime startDate, LocalDateTime endDate) throws InvalidDateException, NotAuthenticatedException, CourseExistException, LessonExistException {
        userServiceLogger.info("start createLesson ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифицирован");
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        if (endDate.isBefore(startDate) || startDate.isBefore(LocalDateTime.now())) {
            userServiceLogger.error("Дата не корректна");
            throw new InvalidDateException("Дата не корректна");
        } else {
            Lesson lesson = lessonRepository.findByDescription(description);
            if (lesson != null) {
                userServiceLogger.error("такое занятие уже существует");
                throw new LessonExistException("такое занятие уже существует");
            }
            if (courseRepository.findByCourseName(courseName) != null) {
                throw new CourseExistException("Такой курс уже существует");
            }
            Course newCourse = new Course(courseName, cost, startDate, endDate);
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
            userServiceLogger.info("end createLesson ");
            return true;
        }
    }

    @Transactional
    public List<ScheduleLessonDto> getSchedule() throws NotAuthenticatedException, NotFoundException {
        userServiceLogger.info("start getSchedule ");
        String login = authenticationFacade.getAuthentication().getName();
        if (login == null) {
            userServiceLogger.error("Юзер не аутентифицирован");
            throw new NotAuthenticatedException("Юзер не аутентифицирован");
        }
        User user = userRepository.findByLogin(login);
        List<Lesson> allUserLessons = new ArrayList<>();
        for (Course course : user.getCourses()) {
            allUserLessons.addAll(course.getLessons());
        }
        if (allUserLessons.isEmpty()) {
            userServiceLogger.error("занятий у юзера нет");
            throw new NotFoundException("занятий у юзера нет");
        }
        userServiceLogger.info("end getSchedule ");
        return scheduleLessonMapper.lessonsToScheduleLessonDtos(allUserLessons);
    }

    @Transactional
    public boolean assignAward(Double award, String login) throws NotFoundException, IncorrectAssignAward {
        userServiceLogger.info("start assignAward ");
        User user = userRepository.findByLogin(login);
        if (user == null) {
            userServiceLogger.error("Юзера с таким логином не существует");
            throw new NotFoundException("Юзера с таким логином не существует");
        }
        if (user.getRole().getRole().equals("ROLE_USER")) {
            throw new IncorrectAssignAward("награда для педагогов");
        }
        user.setAward(award);
        userRepository.save(user);
        userServiceLogger.info("end assignAward ");
        return true;
    }
}
