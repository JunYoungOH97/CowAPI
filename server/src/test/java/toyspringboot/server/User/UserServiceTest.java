package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestModule.ServiceTest;

import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("[Service] 회원가입 테스트")
    public void signUpTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build();

        // when
        UserDto newUser = (UserDto) test(userDto, userService, "signUp");

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
    }

    @Test
    @DisplayName("[Service] 로그인 테스트")
    public void signInTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(Exist_User_password)
                .build();

        // when
        UserDto newUser = (UserDto) test(userDto, userService, "signIn");

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
    }
    
    @Test
    @DisplayName("[Service] 회원 정보 수정 테스트")
    public void updateUserTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .nickname(User_nickname)
                .password(User_password)
                .build();

        // when
        boolean isSuccess = (boolean) test(Exist_User_id, userDto, userService, "updateUser");

        // then
        assertTrue(isSuccess);
    }
}
