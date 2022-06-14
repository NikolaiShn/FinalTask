import com.exceptions.NotFoundException;
import com.model.User;
import com.web.services.UserAuthenticationService;
import com.web.services.UserService;
import com.web.utils.AnnotationWebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
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
public class MockUserAuthenticationService {

    @Test
    void loadUserByUsername() throws NotFoundException {
        String login = "username";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(login, "password",
                new ArrayList<>());
        UserService userService = Mockito.mock(UserService.class);
        UserAuthenticationService userAuthenticationService = Mockito.mock(UserAuthenticationService.class);
        Mockito.when(userService.getUserByLogin(login)).thenReturn(new User());
        Mockito.when(userAuthenticationService.loadUserByUsername(login)).thenReturn(userDetails);
        UserDetails result = userAuthenticationService.loadUserByUsername(login);
        assertEquals(result, userDetails);
    }
}
