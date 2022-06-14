import com.dto.LessonDto;
import com.exceptions.IncorrectInputException;
import com.exceptions.InvalidDateException;
import com.exceptions.NotFoundException;
import com.model.Course;
import com.model.Lesson;
import com.model.LessonForm;
import com.web.dao.CourseRepository;
import com.web.dao.LessonFormRepository;
import com.web.dao.LessonRepository;
import com.web.services.LessonService;
import com.web.utils.AnnotationWebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MockLessonServiceTest {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonFormRepository lessonFormRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Test
    void getAllLessons() {
        List<LessonDto> expectedList = new ArrayList<>();
        Mockito.when(lessonService.getAllLessons()).thenReturn(expectedList);
        List<LessonDto> result = lessonService.getAllLessons();
        assertEquals(expectedList, result);
    }

    @Test
    void findByLessonForm() {
        List<LessonDto> expectedList = new ArrayList<>();
        Mockito.when(lessonService.findByLessonForm("индивидуальное")).thenReturn(expectedList);
        List<LessonDto> result = lessonService.findByLessonForm("индивидуальное");
        assertEquals(expectedList, result);
    }

    @Test
    void getLessonsByCourse() {
        List<LessonDto> expectedList = new ArrayList<>();
        Mockito.when(lessonService.getLessonsByCourse("CourseName")).thenReturn(expectedList);
        List<LessonDto> result = lessonService.getLessonsByCourse("CourseName");
        assertEquals(expectedList, result);
    }

    @Test
    void createLesson() throws InvalidDateException, NotFoundException, IncorrectInputException {
        String lessonForm = "индивидуальное";
        String courseName = "courseName";
        String description = "тест";
        String lessonName = "lessonName";
        Double cost = 50.0;
        LocalDateTime monday = LocalDateTime.of(2022, 8, 8, 12, 20, 0);
        LocalDateTime tuesday = LocalDateTime.of(2022, 8, 9, 12, 20, 0);
        LocalDateTime wednesday = LocalDateTime.of(2022, 8, 10, 12, 20, 0);
        LocalDateTime thursday = LocalDateTime.of(2022, 8, 11, 12, 20, 0);
        LocalDateTime friday = LocalDateTime.of(2022, 8, 12, 12, 20, 0);
        Lesson lesson = new Lesson(23, lessonName, description, new ArrayList(), cost, monday, tuesday, wednesday, thursday, friday);
        Mockito.when(lessonFormRepository.findByFormName(lessonForm)).thenReturn(new LessonForm());
        Mockito.when(courseRepository.findByCourseName(courseName)).thenReturn(new Course(24L, courseName, new ArrayList<>(), new ArrayList<>(), cost, monday, friday));
        Mockito.when(lessonService.createLesson(courseName, lessonName, lessonForm, description,
                     monday, tuesday, wednesday, thursday, friday, cost)).thenReturn(true);
        Mockito.when(lessonRepository.save(Mockito.any(Lesson.class))).thenReturn(lesson);
        boolean result = lessonService.createLesson(courseName, lessonName, lessonForm, description,
                monday, tuesday, wednesday, thursday, friday, cost);
        assertEquals(true, result);
    }

    @Test
    void editLessonName() throws NotFoundException {
        String lessonName = "урок1";
        String courseName = "КурсКниги";
        String newLessonName = "newName";
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        LessonService lessonService = Mockito.mock(LessonService.class);
        Mockito.when(lessonRepository.findLessonByCourseNameAndLessonName(lessonName, courseName)).thenReturn(new Lesson());
        Mockito.when(lessonService.editLessonName(lessonName, newLessonName, courseName)).thenReturn(true);
        boolean result = lessonService.editLessonName(lessonName, newLessonName, courseName);
        assertEquals(true, result);
    }

    @Test
    void editLessonCost() throws NotFoundException {
        String lessonName = "урок1";
        String courseName = "КурсКниги";
        Double cost = 40.0;
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        LessonService lessonService = Mockito.mock(LessonService.class);
        Mockito.when(lessonRepository.findLessonByCourseNameAndLessonName(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(new Lesson());
        Mockito.when(lessonService.editLessonCost(lessonName, cost, courseName)).thenReturn(true);
        boolean result = lessonService.editLessonCost(lessonName, cost, courseName);
        assertEquals(true, result);
    }

    @Test
    void deleteLesson() throws NotFoundException {
        String lessonName = "урок1";
        String courseName = "КурсКниги";
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        LessonService lessonService = Mockito.mock(LessonService.class);
        Mockito.when(lessonRepository.findLessonByCourseNameAndLessonName(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(new Lesson());
        Mockito.when(lessonService.deleteLesson(lessonName, courseName)).thenReturn(true);
        boolean result = lessonService.deleteLesson(lessonName, courseName);
        assertEquals(true, result);
    }

    @Test
    void createLessonReview() throws NotFoundException {
        String lessonDescription = "sdsds";
        String lessonName = "урок1";
        String reviewText = "kakakak";
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonName);
        lesson.setReviews(new ArrayList<>());
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        LessonService lessonService = Mockito.mock(LessonService.class);
        Mockito.when(lessonRepository.findByDescription(lessonDescription)).thenReturn(lesson);
        Mockito.when(lessonService.createLessonReview(lessonDescription, reviewText)).thenReturn(true);
        boolean result = lessonService.createLessonReview(lessonDescription, reviewText);
        assertEquals(true, result);
    }
}
