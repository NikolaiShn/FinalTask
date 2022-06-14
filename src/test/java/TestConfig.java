import com.web.dao.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public CourseRepository testMockCourseRepository() {
        return Mockito.mock(CourseRepository.class);
    }

    @Bean
    @Primary
    public KnowledgeDirectoryRepository testMockKnowledgeDirectoryRepository() {
        return Mockito.mock(KnowledgeDirectoryRepository.class);
    }

    @Bean
    @Primary
    public LessonFormRepository testMockLessonFormRepository() {
        return Mockito.mock(LessonFormRepository.class);
    }

    @Bean
    @Primary
    public LessonRepository testMockLessonRepository() {
        return Mockito.mock(LessonRepository.class);
    }

    @Bean
    @Primary
    public RoleRepository testMockRoleRepository() {
        return Mockito.mock(RoleRepository.class);
    }

    @Bean
    @Primary
    public SectionRepository testMockSectionRepository() {
        return Mockito.mock(SectionRepository.class);
    }

    @Bean
    @Primary
    public ThemeRepository testMockThemeRepository() {
        return Mockito.mock(ThemeRepository.class);
    }

    @Bean
    @Primary
    public UserRepository testMockUserRepository() {
        return Mockito.mock(UserRepository.class);
    }
}