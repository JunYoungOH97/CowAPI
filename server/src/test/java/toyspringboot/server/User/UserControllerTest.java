package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import toyspringboot.server.Controller.UserController;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestConfig.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserControllerTest extends ControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    @DisplayName("[Controller] 회원가입 테스트")
    public void signUpTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(false)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Create_Date)
                .updater(Creator_Member)
                .build();
        when(userService.signUp(any(UserDto.class))).thenReturn(userDto);

        // when
        UserDto userDtoResponse = userController.signUp(userDto);

        // then
        assertEquals(userDto.getEmail(), userDtoResponse.getEmail());
        verify(userService).signUp(any(UserDto.class));
    }
}
