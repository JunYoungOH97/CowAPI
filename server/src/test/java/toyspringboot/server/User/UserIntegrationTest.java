package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.TestConfig.IntegrationTest;

import static toyspringboot.server.User.UserTestConstants.*;

public class UserIntegrationTest extends IntegrationTest {
    @Test
    @DisplayName("[API] 회원가입 테스트")
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
    @DisplayName("[API] 로그인 테스트")
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
    @DisplayName("[API] 사용자 정보 수정 테스트")
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
    @DisplayName("[API] 사용자 삭제 테스트")
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
