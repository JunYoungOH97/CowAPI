package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.OAuthResponseDto;
import toyspringboot.server.Domain.Dto.OAuthUserInfoDto;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.OAuthState;
import toyspringboot.server.Service.OAuthUserService;

@Api(tags = {"OAuth"})
@RequestMapping(value = "/api/v1")
@RestController
@RequiredArgsConstructor
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;
    
    // 1, 2. 로그인 요청하고 Redirect URI 반환 
    @GetMapping("/users/oauth")
    public RedirectURIDto OAuthLogin() {
        System.out.println("jwt test");
        return oAuthUserService.getRedirectURI();
    }
    
    // 4. Authorization Server 로 부터 Code 응답 받고 state 검증 후 Token 요청
    @GetMapping("/oauth/naver")
    public OAuthUserInfoDto OAuthCallback(
                                        @RequestParam(value = "code") String code,
                                        @RequestParam(value = "state") String state) {
        System.out.println("code = " + code + ", state = " + state);

        return oAuthUserService.requestAccessToken(code, state);
    }
}
