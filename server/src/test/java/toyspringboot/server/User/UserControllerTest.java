package toyspringboot.server.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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


    @Test
    @DisplayName("[Controller] 로그인 테스트")
    public void signInTest() throws Exception {
        // given
        String json = getRequestJson(UserDto.builder()
                .email(Exist_User_email)
                .password(Exist_User_password)
                .build());

        // when
        ResultActions actions = postRequest(baseUrl(SignIn_API), json, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] 사용자 정보 수정 테스트")
    public void updateUserTest() throws Exception {
        // given
        String json = getRequestJson(UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .build());

        String header = Exist_User_email;

        // when
        ResultActions actions = putRequest(baseUrl(UpdateUser_API), header, json, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] 사용자 삭제 테스트")
    public void deleteUserTest() throws Exception {
        // given
        String json = getRequestJson(UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .isDeleted(Exist_User_isDeleted)
                .build());

        String header = Exist_User_email;

        // when
        ResultActions actions = deleteRequest(baseUrl(DeleteUser_API), header, json, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
