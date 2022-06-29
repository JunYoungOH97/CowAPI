package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyspringboot.server.Domain.ResponseDto.OAuthUserInfoResponseDto;
import toyspringboot.server.Domain.ResponseDto.RedirectURIResponseDto;
import toyspringboot.server.Service.OAuthUserService;

@Api(tags = {"OAuth"})
@RestController
@RequiredArgsConstructor
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;
    
    // 1, 2. 로그인 요청하고 Redirect URI 반환 
    @ApiOperation(value = "OAuth 로그인", notes = "서버에서 OAuth 로그인 요청을 보내는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/users/oauth")
    public ResponseEntity<RedirectURIResponseDto> OAuthLogin() {
        return new ResponseEntity<>(oAuthUserService.getRedirectURI().toResponse(), HttpStatus.OK);
    }
    
    // 4. Authorization Server 로 부터 Code 응답 받고 state 검증 후 Token 요청
    @ApiOperation(value = "OAuth Token", notes = "Code를 응답 받고 state 검증 후 토큰을 받는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/oauth/naver")
    public ResponseEntity<OAuthUserInfoResponseDto> OAuthCallback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
        return new ResponseEntity<>(oAuthUserService.requestAccessToken(code, state).toResponse(), HttpStatus.OK);
    }
}
