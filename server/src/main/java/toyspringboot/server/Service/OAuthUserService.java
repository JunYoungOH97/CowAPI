package toyspringboot.server.Service;

import com.nimbusds.oauth2.sdk.client.ClientRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.*;
import toyspringboot.server.Domain.Entity.OAuthState;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OAuthUserService {
    private final RedisService redisService;
    private final UserService userService;

    @Value(value = "${spring.security.oauth2.client.registration.naver.client-id}")
    String clientId;

    @Value(value = "${spring.security.oauth2.client.registration.naver.client-secret}")
    String clientSecret;

    @Value(value = "${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
    String grandType;

    @Value(value = "${spring.security.oauth2.client.registration.naver.scope}")
    String scope;

    @Value(value = "${spring.security.oauth2.client.registration.naver.client-name}")
    String clientName;

    @Value(value = "${spring.security.oauth2.client.registration.naver.redirect-uri}")
    String redirectUri;

    @Value(value = "${spring.security.oauth2.client.provider.naver.authorization-uri}")
    String authorizationUri;

    @Value(value = "${spring.security.oauth2.client.provider.naver.token-uri}")
    String tokenUri;

    @Value(value = "${spring.security.oauth2.client.provider.naver.user-info-uri}")
    String userInfoUri;

    @Value(value = "${spring.security.oauth2.client.provider.naver.user-name-attribute}")
    String userName;



    public RedirectURIDto getRedirectURI() {
        // state 생성
        String state = generateState();

        // Redis 에 state 저장
        redisService.saveOAuthState(state);

        // redirect uri 반환
        return RedirectURIDto.builder()
                .redirectURI(generateRedirectURI(state))
                .build();
    }

    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public String generateRedirectURI(String state) {
//        https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트 아이디}&response_type=code&redirect_uri={개발자 센터에 등록한 콜백 URL(URL 인코딩)}&state={상태 토큰}
        return authorizationUri + "?client_id=" + clientId + "&response_type=code&redirect_uri=" + redirectUri + "&state=" + state;
    }

    public OAuthUserInfoDto requestAccessToken(String code, String state) {

        // state 검증
        if(!redisService.getOAuthState(state)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "상태 코드가 일치하지 않습니다.");

        // Authorization Server 로 요청할 WebClient
        WebClient client = WebClient.create("http://localhost:8080");

        // 토큰 요청하고 응답 받기
        OAuthTokenDto accessToken = client.get()
                .uri(generateTokenURI(state, code))
                .retrieve()
                .bodyToMono(OAuthTokenDto.class)
                .block();

        assert accessToken != null;

        // User Info 요청 하고 받기
        OAuthUserInfoDto userInfo = client.post()
                .uri(userInfoUri)
                .header(HttpHeaders.AUTHORIZATION, generateToken(accessToken.getToken_type(), accessToken.getAccess_token()))
                .retrieve()
                .bodyToMono(OAuthUserInfoDto.class)
                .block();

        assert userInfo != null;

        // state 삭제
        redisService.deleteOAuthState(state);

        // 회원등록
        UserDto userDto = UserDto.builder()
                .email(userInfo.getResponse().getEmail())
                .password(userInfo.getResponse().getId())
                .build();

        userService.OAuthUserSave(userDto);

        // 로그인
        // ...

        return userInfo;
    }

    public String generateTokenURI(String state, String code) {
        // https://nid.naver.com/oauth2.0/token?client_id={클라이언트 아이디}&client_secret={클라이언트 시크릿}&grant_type=authorization_code&state={상태 토큰}&code={인증 코드}
        return tokenUri + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=" + grandType + "&state=" + state + "&code=" + code;
    }

    public String generateToken(String type, String token) {
        return "Bearer" + " " + token;
    }
}
