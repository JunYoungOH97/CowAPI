package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.TestModule.ControllerTest;

import static toyspringboot.server.User.UserTestConstants.*;
import static toyspringboot.server.User.UserTestConstants.SignUp_API;
import static toyspringboot.server.QnA.QnATestConstants.*;

public class QnAControllerTest extends ControllerTest {
    @Test
    @DisplayName("[Controller] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        String request = getRequestJson(QnADto.builder()
                .title(QnA_title)
                .content(QnA_content)
                .build()
        );

        String header = Exist_User_email;

        // when
        ResultActions actions = postRequest(baseUrl(QnA_create_API), header, request, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] QnA 조회 테스트")
    public void readTest() throws Exception {
        // given
        String request = getRequestJson(QnADto.builder()
                .id(Exist_QnA_id)
                .build()
        );

        String header = Exist_User_email;

        // when
        ResultActions actions = postRequest(baseUrl(QnA_read_API), header, request, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
