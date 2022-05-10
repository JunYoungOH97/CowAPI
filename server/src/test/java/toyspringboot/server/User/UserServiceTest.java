package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;

import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("[Service] 회원가입 테스트")
    public void signInTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(User_email)
                .password(User_password)
                .nickname(User_nickname)
                .admin(User_admin)
                .build();

        // when
        UserDto newUser = userService.signIn(userDto);

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
    }
}
