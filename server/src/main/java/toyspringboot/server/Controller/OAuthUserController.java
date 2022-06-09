package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.OAuthUserInfoDto;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Service.OAuthUserService;

@Api(tags = {"OAuth"})
@RestController
@RequiredArgsConstructor
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;
    
    // 1, 2. 로그인 요청하고 Redirect URI 반환 
    @GetMapping("/users/oauth")
    public RedirectURIDto OAuthLogin() {
        return oAuthUserService.getRedirectURI();
    }
    
    // 4. Authorization Server 로 부터 Code 응답 받고 state 검증 후 Token 요청
    @GetMapping("/oauth/naver")
    public OAuthUserInfoDto OAuthCallback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
        return oAuthUserService.requestAccessToken(code, state);
    }
}
