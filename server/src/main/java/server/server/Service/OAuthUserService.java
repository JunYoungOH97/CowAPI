//package server.server.Service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ResponseStatusException;
//import server.server.Domain.Dto.*;
//import server.server.Domain.Entity.UserState;
//import server.server.Domain.Repository.UsersRepository;
//import server.server.Redis.StateRedisService;
//
//import java.math.BigInteger;
//import java.security.SecureRandom;
//
//@Service
//@RequiredArgsConstructor
//public class OAuthUserService {
//    private final UserService userService;
//    private final StateRedisService stateRedisService;
//    private final UsersRepository usersRepository;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.client-id}")
//    String clientId;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.client-secret}")
//    String clientSecret;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
//    String grandType;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.scope}")
//    String scope;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.client-name}")
//    String clientName;
//
//    @Value(value = "${spring.security.oauth2.client.registration.naver.redirect-uri}")
//    String redirectUri;
//
//    @Value(value = "${spring.security.oauth2.client.provider.naver.authorization-uri}")
//    String authorizationUri;
//
//    @Value(value = "${spring.security.oauth2.client.provider.naver.token-uri}")
//    String tokenUri;
//
//    @Value(value = "${spring.security.oauth2.client.provider.naver.user-info-uri}")
//    String userInfoUri;
//
//    @Value(value = "${spring.security.oauth2.client.provider.naver.user-name-attribute}")
//    String userName;
//
//    @Value(value = "${AWS.ip}")
//    String AWSip;
//
//    public String generateState() {
//        SecureRandom random = new SecureRandom();
//        return new BigInteger(130, random).toString(32);
//    }
//
//    public RedirectURIDto redirectURI() {
//        // state 생성
//        UserState state = UserState.builder()
//                .state(generateState())
//                .valid(true)
//                .build();
//
//        // Redis 에 state 저장
//        stateRedisService.save(state);
//
//        // redirect uri 반환
//        return RedirectURIDto.builder()
//                .redirectURI(generateRedirectURI(state.getState()))
//                .build();
//    }
//
//    public String generateRedirectURI(String state) {
////        https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트 아이디}&response_type=code&redirect_uri={개발자 센터에 등록한 콜백 URL(URL 인코딩)}&state={상태 토큰}
//        return authorizationUri + "?client_id=" + clientId + "&response_type=code&redirect_uri=" + redirectUri + "&state=" + state;
//    }
//
//    public UsersDto OauthUserSignIn(String code, String state) {
//
//        UserState userState = UserState.builder().state(state).build();
//
//        // state 검증
//        if(stateRedisService.get(userState) == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "상태 코드가 일치하지 않습니다.");
//        if(!stateRedisService.get(userState).getValid()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "상태 코드가 일치하지 않습니다.");
//
//        // Authorization Server 로 요청할 WebClient
//        WebClient client = WebClient.create("http://localhost:8080");
//
//        // 토큰 요청하고 응답 받기
//        OAuthTokenDto accessToken = client.get()
//                .uri(generateTokenURI(state, code))
//                .retrieve()
//                .bodyToMono(OAuthTokenDto.class)
//                .block();
//
//        // state 삭제
//        stateRedisService.delete(userState);
//
//        if(accessToken == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Authorization Server Error");
//
//        // User Info 요청 하고 받기
//        OAuthUserInfoDto userInfo = client.post()
//                .uri(userInfoUri)
//                .header(HttpHeaders.AUTHORIZATION, generateToken(accessToken.getTokenType(), accessToken.getAccessToken()))
//                .retrieve()
//                .bodyToMono(OAuthUserInfoDto.class)
//                .block();
//
//        if(userInfo == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Resource Service Error!");
//
////        // 회원등록, 로그인
//        UsersDto usersDto = UsersDto.builder()
//                .email(userInfo.getResponse().getEmail())
//                .password(userInfo.getResponse().getEmail().substring(7))
//                .build();
//
//        return (!usersRepository.existsByEmail(usersDto.getEmail())) ? userService.signUp(usersDto) : userService.signIn(usersDto);
//    }
//
//    public String generateTokenURI(String state, String code) {
//        // https://nid.naver.com/oauth2.0/token?client_id={클라이언트 아이디}&client_secret={클라이언트 시크릿}&grant_type=authorization_code&state={상태 토큰}&code={인증 코드}
//        return tokenUri + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=" + grandType + "&state=" + state + "&code=" + code;
//    }
//
//    public String generateToken(String type, String token) {
//        return "Bearer" + " " + token;
//    }
//}
