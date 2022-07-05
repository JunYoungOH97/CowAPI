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

@RestController
@RequiredArgsConstructor
public class UserController {
    private final OAuthUserService oAuthUserService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserLoginResponseDto> signUp(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signUp(userDto).toLoginResponse());
    }

    @PostMapping("/signin")
    public ResponseEntity<UserLoginResponseDto> signIn(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signIn(userDto).toLoginResponse());
    }

    @GetMapping("/mypage")
    public ResponseEntity<UserResponseDto> mypage(@RequestHeader(value = "Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(userService.readMypage(TokenDto.builder().accessToken(userToken).build()).toResponse());
    }

    @GetMapping("/reissuance")
    public ResponseEntity<UserResponseDto> reissuance(@RequestHeader(value = "Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(userService.reissuance(TokenDto.builder().accessToken(userToken).build()).toResponse());
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestHeader(value = "Authorization") String userToken,
                                                      @RequestBody UsersDto usersDto) {

        userService.updateUser(TokenDto.builder().accessToken(userToken).build(), usersDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestHeader(value = "Authorization") String userToken) {
        userService.deleteUser(TokenDto.builder().accessToken(userToken).build());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/user/oauth/naver")
    public void OAuthLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(oAuthUserService.redirectURI().getRedirectURI());
    }

    @GetMapping("/oauth/naver")
    public ResponseEntity<UserLoginResponseDto> OAuthCallback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
        return ResponseEntity.ok()
                .body(oAuthUserService.OauthUserSignIn(code, state).toLoginResponse());
    }
}
