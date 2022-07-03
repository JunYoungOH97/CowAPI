package server.server.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Dto.Dto.OAuthUserInfoResponseDto;
import server.server.Dto.Dto.UserDto;
import server.server.Dto.ResposneDto.RedirectURIResponseDto;
import server.server.Dto.ResposneDto.UserResponseDto;
import server.server.Service.OAuthUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final OAuthUserService oAuthUserService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserDto userDto) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                                                        .Authorization("Authorization")
                                                        .isAdmin(true)
                                                        .build();

        return ResponseEntity
                .ok()
                .body(userResponseDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody UserDto userDto) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .Authorization("Authorization")
                .isAdmin(true)
                .build();

        return ResponseEntity
                .ok()
                .body(userResponseDto);
    }

    @GetMapping("/user/oauth/naver")
    public void OAuthLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(oAuthUserService.getRedirectURI().toResponse().getRedirectURI());
    }

    @GetMapping("/oauth/naver")
    public void OAuthCallback(HttpServletResponse response, @RequestParam(value = "code") String code, @RequestParam(value = "state") String state) throws IOException {
        String redirect_uri="http://localhost:8080/dashboard";
        String token = oAuthUserService.requestAccessToken(code, state).toResponse().getEmail();
        response.addHeader("Authorization", token);
        response.sendRedirect(redirect_uri);
    }
}
