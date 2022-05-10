package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.ServerApplicationTests;
import toyspringboot.server.Service.UserService;

import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends ServerApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("[Service] 회원가입 테스트")
    public void signUpTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build();

        // when
        UserDto newUser = userService.signUp(userDto);

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
    }
}
