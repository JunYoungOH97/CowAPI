package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestModuleReflection.ServiceTest;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("[Service] 회원가입 테스트")
    public void signUpTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(User_IsDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Update_Date)
                .updater(Update_Member)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(UserDto.toEntity(userDto));

        // when
//        UserDto newUser = (UserDto) test(userDto, userService, "signUp");
        UserDto newUser = userService.signUp(userDto);

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
        when(userService.signIn(any(UserDto.class))).thenReturn(userDto);

        // when
//        UserDto newUser = (UserDto) test(userDto, userService, "signIn");
        UserDto newUser = userService.signIn(userDto);

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
//        UserDto updateUser = (UserDto) test(userToken, userDto, userService, "updateUser");
        UserDto updateUser = userService.updateUser(userToken, userDto);

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
//        UserDto updateUser = (UserDto) test(userToken, userDto, userService, "deleteUser");
        UserDto updateUser = userService.deleteUser(userToken, userDto);

        // then
        assertEquals(updateUser.getIsDeleted(), User_IsDeleted);
    }
}
