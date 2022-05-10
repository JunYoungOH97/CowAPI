package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.TestModule.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static toyspringboot.server.User.UserTestConstants.*;

public class UserControllerTest extends ControllerTest {
    @Test
    @DisplayName("[Controller] 회원가입 테스트")
    public void signUpTest() throws Exception {
        // given
        String json = getRequestJson(UserDto.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build());

        // when
        boolean isSuccess = sendRequest(SignUp_API, json, MediaType.APPLICATION_JSON);

        // then
        assertTrue(isSuccess);
    }
}
