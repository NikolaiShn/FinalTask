import com.dto.ProfileDto;
import com.model.User;
import com.web.dao.UserRepository;
import com.web.services.ProfileService;
import com.web.utils.AnnotationWebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnotationWebConfig.class, TestConfig.class})
@WebAppConfiguration
public class MockProfileService {

    @Test
    void getProfile() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        ProfileService profileService = Mockito.mock(ProfileService.class);
        ProfileDto profileDto = new ProfileDto("name", "lastName", new ArrayList<>(), new ArrayList<>());
        Mockito.when(userRepository.findCoursesByUserFetch(Mockito.any(User.class))).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.findKnowledgeDirectoriesByUserFetch(Mockito.any(User.class))).thenReturn(new ArrayList<>());
        Mockito.when(profileService.getProfile()).thenReturn(profileDto);
        ProfileDto result = profileService.getProfile();
        assertEquals(result, profileDto);
    }

    @Test
    void changeProfileUserName() {
        String newName = "newName";
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        ProfileService profileService = Mockito.mock(ProfileService.class);
        Mockito.when(userRepository.findByLogin("login")).thenReturn(new User());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
        Mockito.when(profileService.changeProfileUserName(newName)).thenReturn(true);
        boolean result = profileService.changeProfileUserName(newName);
        assertEquals(result, true);
    }

    @Test
    void changeProfileUserLastName() {
        String newName = "newName";
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        ProfileService profileService = Mockito.mock(ProfileService.class);
        Mockito.when(userRepository.findByLogin("login")).thenReturn(new User());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
        Mockito.when(profileService.changeProfileUserLastName(newName)).thenReturn(true);
        boolean result = profileService.changeProfileUserLastName(newName);
        assertEquals(result, true);
    }
}
