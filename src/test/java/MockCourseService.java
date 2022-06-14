import com.dto.CourseDto;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.model.Course;
import com.web.dao.CourseRepository;
import com.web.services.CourseService;
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
public class MockCourseService {

    @Test
    void getAllCourses() {
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Mockito.when(courseRepository.findAll()).thenReturn(new ArrayList<>());
        List<Course> courseList = courseRepository.findAll();
        assertEquals(courseList.size(), 0);
    }

    @Test
    void findByCostGreaterThan() {
        Double cost = 40.0;
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseRepository.findByCostGreaterThan(cost)).thenReturn(new ArrayList<>());
        Mockito.when(courseService.findByCostGreaterThan(cost)).thenReturn(new ArrayList<>());
        List<CourseDto> courseList = courseService.findByCostGreaterThan(cost);
        assertEquals(courseList.size(), 0);
    }

    @Test
    void getAllCoursesAllOrderByEndDateDesc() {
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseRepository.findAllByOrderByEndDateDesc()).thenReturn(new ArrayList<>());
        Mockito.when(courseService.getAllCoursesAllOrderByEndDateDesc()).thenReturn(new ArrayList<>());
        List<CourseDto> courseList = courseService.getAllCoursesAllOrderByEndDateDesc();
        assertEquals(courseList.size(), 0);
    }

    @Test
    void getAllCoursesAllOrderByStartDateDesc() {
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseRepository.findAllByOrderByStartDateDesc()).thenReturn(new ArrayList<>());
        Mockito.when(courseService.getAllCoursesAllOrderByStartDateDesc()).thenReturn(new ArrayList<>());
        List<CourseDto> courseList = courseService.getAllCoursesAllOrderByStartDateDesc();
        assertEquals(courseList.size(), 0);
    }

    @Test
    void createCourse() throws InvalidDateException {
        String courseName = "тест";
        Double cost = 50.0;
        CourseService courseService = Mockito.mock(CourseService.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course = new Course();
        LocalDateTime startDate = LocalDateTime.of(2022, 8, 05, 12, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 8, 15, 12, 10, 0);
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        Mockito.when(courseService.createCourse(courseName, cost, startDate, endDate)).thenReturn(true);
        boolean result = courseService.createCourse(courseName, cost, startDate, endDate);
        assertEquals(true, result);
    }

    @Test
    void deleteCourse() throws NotFoundException {
        String courseName = "courseName";
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseService.deleteCourse(courseName)).thenReturn(true);
        boolean result = courseService.deleteCourse(courseName);
        assertEquals(result, true);
    }

    @Test
    void editCourseCost() throws NotFoundException, IncorrectInputException {
        String courseName = "courseName";
        Double cost = 50.0;
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseService.editCourseCost(courseName, cost)).thenReturn(true);
        boolean result = courseService.editCourseCost(courseName, cost);
        assertEquals(result, true);
    }

    @Test
    void editCourseName() throws NotFoundException {
        String oldName = "courseName";
        String newName = "courseNew";
        Double cost = 50.0;
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        CourseService courseService = Mockito.mock(CourseService.class);
        Mockito.when(courseService.editCourseName(oldName, newName)).thenReturn(true);
        boolean result = courseService.editCourseName(oldName, newName);
        assertEquals(result, true);
    }

    @Test
    void createCourseReview() throws NotFoundException {
        String courseName = "тест";
        String reviewText = "sdsds";
        CourseService courseService = Mockito.mock(CourseService.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course = new Course();
        Mockito.when(courseRepository.findByCourseName(courseName)).thenReturn(course);
        Mockito.when(courseService.createCourseReview(courseName, reviewText)).thenReturn(true);
        boolean result = courseService.createCourseReview(courseName, reviewText);
        assertEquals(true, result);
    }
}
