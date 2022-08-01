package server.server.Users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;
import server.server.Jwt.TokenConverter;
import server.server.Jwt.TokenProvider;
import server.server.Service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static server.server.Users.UsersTestConstants.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UsersRepository userRepository;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private TokenConverter tokenConverter;

    private static UsersDto usersDto;
    private static TokenDto tokenDto;

    @BeforeAll
    static void setUser() {
        usersDto = UsersDto.builder()
                .email(User_email)
                .password(User_password)
                .secretKey(User_secretKey)
                .isAdmin(User_admin)
                .isDeleted(User_deleted)
                .createdAt(User_CreateAt)
                .updater(User_updater)
                .build();

        tokenDto = TokenDto.builder()
                .accessToken(User_accessToken)
                .accessTokenExpiresIn(User_expire)
                .grantType(User_grantType)
                .build();
    }

    @Test
    @DisplayName("[Service] 회원가입 테스트")
    public void signUp() {
        // given
        when(userRepository.save(any(Users.class))).thenReturn(UsersDto.toEntity(usersDto));
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(UsersDto.toEntity(usersDto)));
        when(tokenProvider.generateTokenDto(any(Authentication.class))).thenReturn(tokenDto);

        // when
        UsersDto newUser = userService.signUp(usersDto);

        // then
        verify(userRepository).save(any(Users.class));
        verify(userRepository).findByEmail(any(String.class));
        verify(tokenProvider).generateTokenDto(any(Authentication.class));
    }

    @Test
    @DisplayName("[Service] 로그인 테스트")
    public void signIn() {
        // given
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(UsersDto.toEntity(usersDto)));
        when(tokenProvider.generateTokenDto(any(Authentication.class))).thenReturn(tokenDto);

        // when
        UsersDto loginUser = userService.signIn(usersDto);

        // then
        verify(userRepository).findByEmail(any(String.class));
        verify(tokenProvider).generateTokenDto(any(Authentication.class));
    }

    @Test
    @DisplayName("[Service] 회원 수정 테스트")
    public void update() {
        // given
        UsersDto updateInfo = UsersDto.builder()
                .password("update")
                .build();

        when(tokenConverter.getUser(any(TokenDto.class))).thenReturn(UsersDto.toEntity(usersDto));
        doNothing().when(userRepository).updateUser(any(Users.class), any(UsersDto.class));

        // when
        userService.updateUser(tokenDto, updateInfo);

        // then
        verify(tokenConverter).getUser(any(TokenDto.class));
        verify(userRepository).updateUser(any(Users.class), any(UsersDto.class));
    }

    @Test
    @DisplayName("[Service] 회원 삭제 테스트")
    public void delete() {
        // given
        when(tokenConverter.getUser(any(TokenDto.class))).thenReturn(UsersDto.toEntity(usersDto));
        doNothing().when(userRepository).deleteUser(any(Users.class));

        // when
        userService.deleteUser(tokenDto);

        // then
        verify(tokenConverter).getUser(any(TokenDto.class));
        verify(userRepository).deleteUser(any(Users.class));
    }
}
