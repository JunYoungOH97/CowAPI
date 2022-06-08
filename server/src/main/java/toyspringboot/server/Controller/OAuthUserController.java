package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.OAuthUserInfoDto;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.OAuthState;
import toyspringboot.server.Service.OAuthUserService;

@Api(tags = {"사용자"})
@RestController
//@EnableWebSecurity
@RequiredArgsConstructor
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;
    
    // 1, 2. 로그인 요청하고 Redirect URI 반환 
    @GetMapping("/users/oauth")
    public RedirectURIDto OAuthLogin(@RequestBody UserDto userDto, @RequestParam(value = "resourceServer") String resourceServer) {
        return oAuthUserService.getRedirectURI(userDto, resourceServer);
    }
    
    // 4. Authorization Server 로 부터 Code 응답 받기
    @GetMapping("/oauth/{resourceServer}")
    public OAuthUserInfoDto OAuthCallback(@PathVariable(value = "resourceServer") String resourceServer,
                                          @RequestParam(value = "code") String code,
                                          @RequestParam(value = "state") String state) {
        return oAuthUserService.requestAccessToken(resourceServer, code, state);
    }
}
