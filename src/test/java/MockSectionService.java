import com.dto.SectionDto;
import com.exceptions.NotFoundException;
import com.model.KnowledgeDirectory;
import com.model.Section;
import com.web.dao.KnowledgeDirectoryRepository;
import com.web.dao.SectionRepository;
import com.web.services.SectionService;
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
public class MockSectionService {

    @Test
    void getAllSections() {
        SectionService sectionService = Mockito.mock(SectionService.class);
        SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
        Mockito.when(sectionRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(sectionService.getAllSections()).thenReturn(new ArrayList<>());
        List<SectionDto> result = sectionService.getAllSections();
        assertEquals(result.size(), 0);
    }

    @Test
    void createSection() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String sectionName = "name";
        SectionService sectionService = Mockito.mock(SectionService.class);
        SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        Mockito.when(knowledgeDirectoryRepository.findByName(knowledgeDirectoryName)).thenReturn(new KnowledgeDirectory());
        Mockito.when(sectionRepository.save(Mockito.any(Section.class))).thenReturn(new Section());
        Mockito.when(sectionService.createSection(knowledgeDirectoryName, sectionName)).thenReturn(true);
        boolean result = sectionService.createSection(knowledgeDirectoryName, sectionName);
        assertEquals(true, result);
    }

    @Test
    void editSectionName() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String sectionName = "name";
        String newSectionName = "name";
        SectionService sectionService = Mockito.mock(SectionService.class);
        SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
        Mockito.when(sectionRepository.findSectionByNameAndKnowledgeDirectoryName(sectionName, knowledgeDirectoryName)).thenReturn(new Section());
        Mockito.when(sectionService.editSectionName(sectionName, newSectionName, knowledgeDirectoryName)).thenReturn(true);
        boolean result = sectionService.editSectionName(sectionName, newSectionName, knowledgeDirectoryName);
        assertEquals(true, result);
    }

    @Test
    void deleteSection() throws NotFoundException {
        String knowledgeDirectoryName = "name";
        String sectionName = "name";
        SectionService sectionService = Mockito.mock(SectionService.class);
        SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
        KnowledgeDirectoryRepository knowledgeDirectoryRepository = Mockito.mock(KnowledgeDirectoryRepository.class);
        Mockito.when(sectionRepository.findSectionByNameAndKnowledgeDirectoryName(sectionName, knowledgeDirectoryName)).thenReturn(new Section());
        Mockito.when(knowledgeDirectoryRepository.save(Mockito.any(KnowledgeDirectory.class))).thenReturn(new KnowledgeDirectory());
        Mockito.when(sectionService.deleteSection(sectionName, knowledgeDirectoryName)).thenReturn(true);
        boolean result = sectionService.deleteSection(sectionName, knowledgeDirectoryName);
        assertEquals(true, result);
    }

}
