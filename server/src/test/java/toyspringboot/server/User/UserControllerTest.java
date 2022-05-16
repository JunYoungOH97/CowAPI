package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import toyspringboot.server.Controller.UserController;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.TestModule.ControllerTest;

import static toyspringboot.server.User.UserTestConstants.*;

public class UserControllerTest extends ControllerTest {
    @Test
    @DisplayName("[Controller] 회원가입 테스트")
    public void signUpTest() throws Exception {
        // given
        String request = getRequestJson(UserDto.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build());

        // when
        ResultActions actions = sendRequest(baseUrl(SignUp_API), request, MediaType.APPLICATION_JSON);

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
        ResultActions actions = sendRequest(baseUrl(SignIn_API), json, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] 사용자 정보 수정 테스트")
    public void updateUserTest() throws Exception {
        // given
        String json = getRequestJson(UserDto.builder()
                .nickname(User_nickname)
                .password(User_password)
                .build());

        String header = Exist_User_id.toString();

        // when
        ResultActions actions = sendRequest(baseUrl(UpdateUser_API), header,json, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
