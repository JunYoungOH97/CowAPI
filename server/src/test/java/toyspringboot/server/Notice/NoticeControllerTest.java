//package toyspringboot.server.Notice;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import toyspringboot.server.Domain.Dto.NoticeDto;
//import toyspringboot.server.TestConfig.IntegrationTest;
//import toyspringboot.server.UserAuthentication.UserAuthenticationProvider;
//
//import static toyspringboot.server.User.UserTestConstants.Exist_User_email;
//
//import static toyspringboot.server.Notice.NoticeSuccessConstants.*;
//
//public class NoticeControllerTest extends IntegrationTest {
//    @Autowired
//    private UserAuthenticationProvider userAuthenticationProvider;
//    private ResultActions actions;
//
//    @Test
//    @DisplayName("[Controller] 공지 생성 테스트")
//    public void createTest() throws Exception {
//        // given
//        String request = getRequestJson(NoticeDto.builder()
//                .title(Test_Notice_title)
//                .content(Test_Notice_content)
//                .build()
//        );
//
////        String JwtToken = userAuthenticationProvider.generateToken(Exist_User_email);
//        String JwtToken = userAuthenticationProvider.generateToken("User");
//        System.out.println(JwtToken);
//
//        // when
//        ResultActions actions = postRequest(baseUrl(Notice_create_API), JwtToken, request, MediaType.APPLICATION_JSON);
//
//        // then
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @DisplayName("[Controller] 공지 조회 테스트")
//    public void readTest() throws Exception {
//        // given
//        String JwtToken = userAuthenticationProvider.generateToken(Exist_User_email);
//
//        // when
//        ResultActions actions = getRequest(baseUrl(Notice_read_API), JwtToken, MediaType.APPLICATION_JSON);
//
//        // then
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    @DisplayName("[Controller] 공지 수정 테스트")
//    public void updateTest() throws Exception {
//        // given
//        String request = getRequestJson(NoticeDto.builder()
//                .id(Exist_Notice_id)
//                .title(Test_Notice_title)
//                .content(Test_Notice_content)
//                .build()
//        );
//
//        String JwtToken = userAuthenticationProvider.generateToken(Exist_User_email);
//
//        // when
//        ResultActions actions = putRequest(baseUrl(Notice_update_API), JwtToken, request, MediaType.APPLICATION_JSON);
//
//        // then
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//    @Test
//    @DisplayName("[Controller] 공지 삭제 테스트")
//    public void deleteTest() throws Exception {
//        // given
//        String request = getRequestJson(NoticeDto.builder()
//                .id(Exist_Notice_id)
//                .build()
//        );
//
//        String JwtToken = userAuthenticationProvider.generateToken(Exist_User_email);
//
//
//        // when
//        ResultActions actions = deleteRequest(baseUrl(Notice_delete_API), JwtToken, MediaType.APPLICATION_JSON);
//
//        // then
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}
