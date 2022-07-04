package server.server.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Dto.UsersDto;
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
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signUp(userDto).toResponse());
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody UsersDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.signIn(userDto).toResponse());
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
