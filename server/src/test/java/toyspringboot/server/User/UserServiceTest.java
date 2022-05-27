package toyspringboot.server.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestConfig.ServiceTest;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static toyspringboot.server.User.UserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends ServiceTest {
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
        UserDto newUser = userService.signUp(userDto);

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("[Service] 로그인 테스트")
    public void signInTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(Exist_User_password)
                .admin(User_admin)
                .isDeleted(User_IsDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Update_Date)
                .updater(Update_Member)
                .build();

        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(UserDto.toEntity(userDto)));

        // when
        UserDto newUser = userService.signIn(userDto);

        // then
        assertEquals(userDto.getEmail(), newUser.getEmail());
        verify(userRepository).findByEmail(anyString());
    }

    @Test
    @DisplayName("[Service] 회원 정보 수정 테스트")
    public void updateUserTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(User_IsDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Update_Date)
                .updater(Update_Member)
                .build();

        String userToken = "testToken";
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(UserDto.toEntity(userDto)));

        // when
        UserDto updateUser = userService.updateUser(userToken, userDto);

        // then
        assertEquals(updateUser.getPassword(), User_password);
        verify(userRepository).findByEmail(anyString());
    }

    @Test
    @DisplayName("[Service] 회원 삭제 테스트")
    public void deleteUserTest() {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(User_password)
                .admin(User_admin)
                .isDeleted(User_IsDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Update_Date)
                .updater(Update_Member)
                .build();

        String userToken = "testToken";
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(UserDto.toEntity(userDto)));

        // when
        UserDto updateUser = userService.deleteUser(userToken, userDto);

        // then
        assertEquals(updateUser.getIsDeleted(), User_IsDeleted);
        verify(userRepository).findByEmail(anyString());
    }
}
