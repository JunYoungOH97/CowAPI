package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.TestConfig.IntegrationTest;

import static toyspringboot.server.QnA.QnATestConstants.*;
import static toyspringboot.server.QnA.QnATestConstants.QnA_Page_API;
import static toyspringboot.server.User.UserTestConstants.Exist_User_email;

public class QnAIntegrationTest extends IntegrationTest {
    @Test
    @DisplayName("[Controller] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        String request = getRequestJson(QnADto.builder()
                .title(QnA_title)
                .isDeleted(QnA_isDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Create_Date)
                .updater(Creator_Member)
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
        String header = Exist_User_email;

        // when
        ResultActions actions = getRequest(baseUrl(QnA_read_API), header, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] QnA 수정 테스트")
    public void updateTest() throws Exception {
        // given
        String request = getRequestJson(QnADto.builder()
                .id(Exist_QnA_id)
                .title(QnA_title)
                .content(QnA_content)
                .build()
        );

        String header = Exist_User_email;

        // when
        ResultActions actions = putRequest(baseUrl(QnA_update_API), header, request, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] QnA 삭제 테스트")
    public void deleteTest() throws Exception {
        // given
        String request = getRequestJson(QnADto.builder()
                .id(Exist_QnA_id)
                .title(QnA_title)
                .content(QnA_content)
                .build()
        );

        String header = Exist_User_email;

        // when
        ResultActions actions = deleteRequest(baseUrl(QnA_delete_API), header, request, MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] QnA 검색 테스트")
    public void searchTest() throws Exception {
        // given
        // when
        ResultActions actions = getRequest(baseUrl(QnA_Query_API), MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("[Controller] QnA page 테스트")
    public void pageQnA() throws Exception {
        // given
        // when
        ResultActions actions = getRequest(baseUrl(QnA_Page_API), MediaType.APPLICATION_JSON);

        // then
        actions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
