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
                .email(Exist_User_email)
                .password(User_password)
                .build();

        String userToken = "testToken";

        // when
        UserDto updateUser = (UserDto) test(userToken, userDto, userService, "updateUser");

        // then
        assertEquals(updateUser.getPassword(), User_password);
    }

    @Test
    @DisplayName("[Service] 회원 삭제 테스트")
    public void deleteUserTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .isDeleted(User_IsDeleted)
                .build();

        String userToken = "testToken";

        // when
        UserDto updateUser = (UserDto) test(userToken, userDto, userService, "deleteUser");

        // then
        assertEquals(updateUser.getIsDeleted(), User_IsDeleted);
    }
}
