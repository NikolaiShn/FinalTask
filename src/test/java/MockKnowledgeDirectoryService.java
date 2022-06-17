import com.dto.KnowledgeDirectoryDto;
import com.dto.SectionDto;
import com.dto.ThemeDto;
import com.exceptions.NotFoundException;
import com.exceptions.SectionExistException;
import com.exceptions.ThemeExistException;
import com.model.KnowledgeDirectory;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.services.KnowledgeDirectoryService;
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
public class MockKnowledgeDirectoryService {

    @Test
    void getAllKnowledgeDirectories() {
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        Mockito.when(knowledgeDirectoryService.getAllKnowledgeDirectories()).thenReturn(new ArrayList<>());
        List<KnowledgeDirectoryDto> result = knowledgeDirectoryService.getAllKnowledgeDirectories();
        assertEquals(result.size(), 0);
    }

    @Test
    void findKnowledgeDirectoryThemes() {
        String knowledgeDirectoryName = "name";
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        Mockito.when(knowledgeDirectoryService.findKnowledgeDirectoryThemes(knowledgeDirectoryName)).thenReturn(new ArrayList<>());
        List<ThemeDto> result = knowledgeDirectoryService.findKnowledgeDirectoryThemes(knowledgeDirectoryName);
        assertEquals(result.size(), 0);
    }

    @Test
    void findKnowledgeDirectorySections() {
        String knowledgeDirectoryName = "name";
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        Mockito.when(knowledgeDirectoryService.findKnowledgeDirectorySections(knowledgeDirectoryName)).thenReturn(new ArrayList<>());
        List<SectionDto> result = knowledgeDirectoryService.findKnowledgeDirectorySections(knowledgeDirectoryName);
        assertEquals(result.size(), 0);
    }

    @Test
    void createKnowLedgeDirectory() {
        String knowledgeDirectoryName = "name";
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        Mockito.when(knowledgeDirectoryRepository.save(Mockito.any(KnowledgeDirectory.class))).thenReturn(knowledgeDirectory);
        Mockito.when(knowledgeDirectoryService.createKnowLedgeDirectory(knowledgeDirectoryName)).thenReturn(true);
        boolean result = knowledgeDirectoryService.createKnowLedgeDirectory(knowledgeDirectoryName);
        assertEquals(result, true);
    }

    @Test
    void deleteKnowledgeDirectory() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        Mockito.when(knowledgeDirectoryService.deleteKnowledgeDirectory(knowledgeDirectoryName)).thenReturn(true);
        boolean result = knowledgeDirectoryService.deleteKnowledgeDirectory(knowledgeDirectoryName);
        assertEquals(result, true);
    }

    @Test
    void editKnowledgeDirectoryName() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String newKnowledgeDirectoryName = "newName";
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        Mockito.when(knowledgeDirectoryService.editKnowledgeDirectoryName(newKnowledgeDirectoryName,knowledgeDirectoryName)).thenReturn(true);
        boolean result = knowledgeDirectoryService.editKnowledgeDirectoryName(newKnowledgeDirectoryName,knowledgeDirectoryName);
        assertEquals(result, true);
    }

    @Test
    void addTheme() throws NotFoundException, ThemeExistException {
        String knowledgeDirectoryName = "name";
        String themeName = "name";
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        Mockito.when(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName)).thenReturn(knowledgeDirectory);
        Mockito.when(knowledgeDirectoryService.addTheme(knowledgeDirectoryName, themeName)).thenReturn(true);
        boolean result = knowledgeDirectoryService.addTheme(knowledgeDirectoryName, themeName);
        assertEquals(result, true);
    }

    @Test
    void addSection() throws NotFoundException, SectionExistException {
        String knowledgeDirectoryName = "name";
        String sectionName = "name";
        KnowledgeDirectory knowledgeDirectory = new KnowledgeDirectory();
        KnowledgeDirectoryService knowledgeDirectoryService = Mockito.mock(KnowledgeDirectoryService.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        knowledgeDirectoryRepository.findByName(knowledgeDirectoryName);
        Mockito.when(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName)).thenReturn(knowledgeDirectory);
        Mockito.when(knowledgeDirectoryService.addSection(knowledgeDirectoryName, sectionName)).thenReturn(true);
        boolean result = knowledgeDirectoryService.addSection(knowledgeDirectoryName, sectionName);
        assertEquals(result, true);
    }
}
