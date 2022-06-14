import com.dto.CourseDto;
import com.dto.LessonDto;
import com.dto.ScheduleLessonDto;
import com.exceptions.*;
import com.model.*;
import com.web.dao.*;
import com.web.services.UserService;
import com.web.utils.AnnotationWebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnotationWebConfig.class, TestConfig.class})
@WebAppConfiguration
public class MockUserService {

    @Test
    void getUserByLogin() throws NotFoundException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userService.getUserByLogin(login)).thenReturn(user);
        User result = userService.getUserByLogin(login);
        assertEquals(result, user);
    }

    @Test
    void getCurrentUserAccessibleLessons() throws NotAuthenticatedException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.findCoursesByUserFetch(user)).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findLessonsByCourseFetch("courseName")).thenReturn(new ArrayList<>());
        Mockito.when(userService.getCurrentUserAccessibleLessons()).thenReturn(new ArrayList<>());
        List<LessonDto> result = userService.getCurrentUserAccessibleLessons();
        assertEquals(result.size(), 0);
    }

    @Test
    void getCurrentUserAccessibleCourses() throws NotAuthenticatedException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.findCoursesByUserFetch(user)).thenReturn(new ArrayList<>());
        Mockito.when(userService.getCurrentUserAccessibleCourses()).thenReturn(new ArrayList<>());
        List<CourseDto> result = userService.getCurrentUserAccessibleCourses();
        assertEquals(result.size(), 0);
    }

    @Test
    void getCurrentUserAccessibleCoursesSortByCost() throws NotAuthenticatedException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.findCoursesByUserFetch(user)).thenReturn(new ArrayList<>());
        Mockito.when(userService.getCurrentUserAccessibleCoursesSortByCost()).thenReturn(new ArrayList<>());
        List<CourseDto> result = userService.getCurrentUserAccessibleCoursesSortByCost();
        assertEquals(result.size(), 0);
    }

    @Test
    void getCurrentUserAccessibleLessonsSortByCost() throws NotAuthenticatedException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.findCoursesByUserFetch(user)).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findLessonsByCourseFetch("courseName")).thenReturn(new ArrayList<>());
        Mockito.when(userService.getCurrentUserAccessibleLessonsSortByCost()).thenReturn(new ArrayList<>());
        List<LessonDto> result = userService.getCurrentUserAccessibleLessonsSortByCost();
        assertEquals(result.size(), 0);
    }

    @Test
    void registerUser() throws UserExistException {
        String login = "login";
        User user = new User();
        Role role = new Role();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(roleRepository.findByRole("ADMIN")).thenReturn(role);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.registerUser("user", "password", "ADMIN", "name", "lastName")).thenReturn(true);
        boolean result = userService.registerUser("user", "password", "ADMIN", "name", "lastName");
        assertEquals(result, true);
    }

    @Test
    void subscribeToCourse() throws NotFoundException, NotAuthenticatedException, CourseExistException {
        String login = "login";
        String courseName = "courseName";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(courseRepository.findByCourseName(courseName)).thenReturn(new Course());
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.subscribeToCourse(courseName)).thenReturn(true);
        boolean result = userService.subscribeToCourse(courseName);
        assertEquals(result, true);
    }

    @Test
    void subscribeToLesson() throws NotFoundException, NotAuthenticatedException {
        String login = "login";
        String courseName = "courseName";
        String description = "best lesson";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(lessonRepository.findByDescription(description)).thenReturn(new Lesson());
        Mockito.when(courseRepository.findByCourseName(courseName)).thenReturn(new Course());
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.subscribeToLesson(courseName)).thenReturn(true);
        boolean result = userService.subscribeToLesson(courseName);
        assertEquals(result, true);
    }

    @Test
    void createCourse() throws InvalidDateException, NotAuthenticatedException, CourseExistException {
        String login = "login";
        String courseName = "courseName";
        Double cost = 50.0;
        LocalDateTime startDate = LocalDateTime.of(2022, 8, 05, 12, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 8, 15, 12, 10, 0);
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.createCourse(courseName, cost, startDate, endDate)).thenReturn(true);
        boolean result = userService.createCourse(courseName, cost, startDate, endDate);
        assertEquals(result, true);
    }

    @Test
    void createLesson() throws InvalidDateException, NotAuthenticatedException, CourseExistException, LessonExistException {
        String login = "login";
        String lessonName = "lessonName";
        String lessonFormName = "индивидуальное";
        String description = "best lesson";
        Double cost = 50.0;
        LocalDateTime startDate = LocalDateTime.of(2022, 8, 05, 12, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 8, 15, 12, 10, 0);
        User user = new User();
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        LessonFormRepository lessonFormRepository = Mockito.mock(LessonFormRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(lessonRepository.findByLessonName(lessonName)).thenReturn(new Lesson());
        Mockito.when(lessonFormRepository.findByFormName(lessonFormName)).thenReturn(new LessonForm());
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userService.createLesson(lessonName, description, cost, startDate, endDate)).thenReturn(true);
        boolean result = userService.createLesson(lessonName, description, cost, startDate, endDate);
        assertEquals(result, true);
    }

    @Test
    void getSchedule() throws NotAuthenticatedException, NotFoundException {
        String login = "login";
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userService.getSchedule()).thenReturn(new ArrayList<>());
        List<ScheduleLessonDto> result = userService.getSchedule();
        assertEquals(result.size(), 0);
    }

    @Test
    void assignAward() throws NotFoundException {
        String login = "login";
        Double award = 50.0;
        User user = new User();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        Mockito.when(userService.assignAward(award, login)).thenReturn(true);
        boolean result = userService.assignAward(award, login);
        assertEquals(result, true);
    }
}
