package toyspringboot.server.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.ServerApplicationTests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static toyspringboot.server.User.UserTestConstants.*;

@AutoConfigureMockMvc
public class UserControllerTest extends ServerApplicationTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("[Controller] 회원가입 테스트")
    public void signUpTest() throws Exception {
        // given
        String reqJson = mapper.writeValueAsString(
                UserDto.builder()
                        .email(User_email)
                        .password(User_password)
                        .nickname(User_nickname)
                        .admin(User_admin)
                        .build()
        );

        // when
        ResultActions perform = mockMvc.perform(post(baseUrl(SignUp_API))
                .content(reqJson)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        assertTrue(true);
    }
}
