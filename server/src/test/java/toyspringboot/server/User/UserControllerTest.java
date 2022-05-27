package toyspringboot.server.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import toyspringboot.server.Controller.UserController;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestConfig.ControllerTest;

import static toyspringboot.server.User.UserTestConstants.*;


@WebMvcTest(UserController.class)
public class UserControllerTest extends ControllerTest {
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("[Controller] 회원가입 테스트")
    public void signUpTest() throws Exception {
        // given
        String request = getRequestJson(UserDto.builder()
                .email(User_email)
                .password(User_password)
                .admin(User_admin)
                .build());
        // when
        ResultActions actions = postRequest(baseUrl(SignUp_API), request, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
