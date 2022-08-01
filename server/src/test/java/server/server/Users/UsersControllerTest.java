//package server.server.Users;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import server.server.Domain.Dto.TokenDto;
//import server.server.Domain.Dto.UsersDto;
//import server.server.TestConfig.ControllerTestConfig;
//
//import static server.server.Users.UsersTestConstants.*;
//import static server.server.Users.UsersTestConstants.User_grantType;
//
//
//public class UsersControllerTest extends ControllerTestConfig {
//    private static UsersDto usersDto;
//    private static TokenDto tokenDto;
//
//    @BeforeAll
//    static void setUser() {
//        usersDto = UsersDto.builder()
//                .email(User_email)
//                .password(User_password)
//                .secretKey(User_secretKey)
//                .isAdmin(User_admin)
//                .isDeleted(User_deleted)
//                .createdAt(User_CreateAt)
//                .updater(User_updater)
//                .build();
//
//        tokenDto = TokenDto.builder()
//                .accessToken(User_accessToken)
//                .accessTokenExpiresIn(User_expire)
//                .grantType(User_grantType)
//                .build();
//    }
//
//    @Test
//    @DisplayName("[Controller] 회원가입 테스트")
//    public void signUp() throws Exception {
//        // given
//        String request = getRequestJson(usersDto);
//
//        // when
//        ResultActions actions = postRequest(baseUrl(SignUp_API), request, MediaType.APPLICATION_JSON);
//
//        // then
//        actions.andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}
