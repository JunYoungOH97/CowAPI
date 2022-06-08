package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Service.OAuthUserService;

@Api(tags = {"사용자"})
@RequestMapping(value = "/api/v1")
@RestController
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;

    @GetMapping("users/oauth")
    public RedirectURIDto OAuthLogin(@RequestBody UserDto userDto, @RequestParam String resourceServer) {
        return oAuthUserService.getRedirectURI(userDto, resourceServer);
    }



}
