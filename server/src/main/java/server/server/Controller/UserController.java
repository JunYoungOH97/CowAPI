package server.server.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.ResposneDto.UserLoginResponseDto;
import server.server.Domain.ResposneDto.UserResponseDto;
import server.server.Service.OAuthUserService;
import server.server.Service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final OAuthUserService oAuthUserService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserLoginResponseDto> signUp(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signUp(userDto).toResponse());
    }

    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponseDto> signIn(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signIn(userDto).toResponse());
    }

    @GetMapping("/mypage")
    public ResponseEntity<UserResponseDto> mypage(@RequestHeader(value = "Authorization") String token) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .email("test")
                .createdAt(now)
                .secretKey("123123")
                .build();

        return ResponseEntity.ok()
                .body(userResponseDto);
    }

    @GetMapping("/reissuance")
    public ResponseEntity<UserResponseDto> reissuance(@RequestHeader(value = "Authorization") String token) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .email("test")
                .createdAt(now)
                .secretKey("123123")
                .build();

        return ResponseEntity.ok()
                .body(userResponseDto);
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestHeader(value = "Authorization") String token,
                                                      @RequestBody UsersDto usersDto) {

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

//    @GetMapping("/user/oauth/naver")
//    public void OAuthLogin(HttpServletResponse response) throws IOException {
//        response.sendRedirect(oAuthUserService.getRedirectURI().getRedirectURI());
//    }
//
//    @GetMapping("/oauth/naver")
//    public void OAuthCallback(HttpServletResponse response, @RequestParam(value = "code") String code, @RequestParam(value = "state") String state) throws IOException {
//        String redirect_uri = "http://localhost:8080/";
//        TokenDto tokenDto = oAuthUserService.OauthUserSignIn(code, state);
//
//        System.out.println("response = " + tokenDto.getAccessToken());
//
//        response.addHeader("Authorization", tokenDto.getAccessToken());
//        response.sendRedirect(redirect_uri);
//    }
}
