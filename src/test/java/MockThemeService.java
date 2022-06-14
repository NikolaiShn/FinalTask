import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Theme;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.ThemeRepository;
import com.web.services.ThemeService;
import com.web.utils.AnnotationWebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnotationWebConfig.class, TestConfig.class})
@WebAppConfiguration
public class MockThemeService {

    @Test
    void getAllThemes() {
        ThemeRepository themeRepository = Mockito.mock(ThemeRepository.class);
        ThemeService themeService = Mockito.mock(ThemeService.class);
        Mockito.when(themeRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(themeService.getAllThemes()).thenReturn(new ArrayList<>());
        List<ThemeDto> themeDtos = themeService.getAllThemes();
        assertEquals(themeDtos.size(), 0);
    }

    @Test
    void createTheme() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String themeName = "name";
        ThemeService themeService = Mockito.mock(ThemeService.class);
        ThemeRepository themeRepository = Mockito.mock(ThemeRepository.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        Mockito.when(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName)).thenReturn(new KnowledgeDirectory());
        Mockito.when(themeRepository.save(Mockito.any(Theme.class))).thenReturn(new Theme());
        Mockito.when(themeService.createTheme(knowledgeDirectoryName, themeName)).thenReturn(true);
        boolean result = themeService.createTheme(knowledgeDirectoryName, themeName);
        assertEquals(true, result);
    }

    @Test
    void editThemeName() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String themeName = "name";
        String newThemeName = "newThemeName";
        ThemeService themeService = Mockito.mock(ThemeService.class);
        ThemeRepository themeRepository = Mockito.mock(ThemeRepository.class);
        Mockito.when(themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName)).thenReturn(new Theme());
        Mockito.when(themeService.editThemeName(themeName, newThemeName, knowledgeDirectoryName)).thenReturn(true);
        boolean result = themeService.editThemeName(themeName, newThemeName, knowledgeDirectoryName);
        assertEquals(true, result);
    }

    @Test
    void deleteTheme() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String themeName = "name";
        ThemeService themeService = Mockito.mock(ThemeService.class);
        ThemeRepository themeRepository = Mockito.mock(ThemeRepository.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        Mockito.when(themeRepository.findThemeByNameAndKnowledgeDirectory(themeName, knowledgeDirectoryName)).thenReturn(new Theme());
        Mockito.when(knowledgeDirectoryRepository.save(Mockito.any(KnowledgeDirectory.class))).thenReturn(new KnowledgeDirectory());
        Mockito.when(themeService.deleteTheme(themeName, knowledgeDirectoryName)).thenReturn(true);
        boolean result = themeService.deleteTheme(themeName, knowledgeDirectoryName);
        assertEquals(true, result);
    }
}
