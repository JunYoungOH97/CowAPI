package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.OAuthTokenDto;
import toyspringboot.server.Domain.Dto.OAuthUserInfoDto;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.OAuthState;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {
    private final RedisService redisService;

    @Value(value = "${security.oauth2.client.registration.naver.redirect-uri}")
    String redirectURI;

    @Value(value = "${security.oauth2.client.registration.naver.clientId}")
    String clientId;

    @Value(value = "${security.oauth2.client.registration.naver.clientSecret}")
    String clientSecret;

    @Value(value = "${security.oauth2.client.registration.naver.scope}")
    String clientScope;

    @Value(value = "${oauth.callBackURL}")
    String callBackURL;

    public RedirectURIDto getRedirectURI(UserDto userDto, String resourceServer) {
        String state = generateState();

        OAuthState oAuthState = new OAuthState(userDto.getEmail(), state);
        redisService.saveOAuthState(oAuthState);

        return RedirectURIDto.builder()
                .redirectURI(generateRedirectURI(resourceServer, oAuthState))
                .build();
    }

    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public String generateRedirectURI(String resourceServer, OAuthState oAuthState) {
        return redirectURI + "?response_type=code" + "&client_id=" + clientId + "&state=" + oAuthState.getState() + "&redirect_uri=" + callBackURL + resourceServer;
    }

    public OAuthUserInfoDto requestAccessToken(String resourceServer, String code, String state) {
        // Authorization Server 로 Access 토큰 요청하고 응답 받기
        WebClient client = WebClient.create("http://localhost:8080");

        OAuthTokenDto accessToken = client.get()
                .uri(generateResourceServerRequest(resourceServer, state, code))
                .retrieve()
                .bodyToMono(OAuthTokenDto.class)
                .block();

        // User Info 요청 하고 받기
        OAuthUserInfoDto userInfo = client.post()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header(HttpHeaders.AUTHORIZATION, Objects.requireNonNull(accessToken).getToken_type() + " " + accessToken.getAccess_token())
                .retrieve()
                .bodyToMono(OAuthUserInfoDto.class)
                .block();

        // state 검증
        OAuthState redisState = redisService.getOAuthState(Objects.requireNonNull(userInfo).getEmail());

        if(!redisState.getState().equals(state)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "상태 코드가 일치하지 않습니다.");

        return userInfo;
    }

    public String generateResourceServerRequest(String resourceServer, String state, String code) {
        return "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=authorization_code&state=" + state + "&code=" + code;
    }
}
