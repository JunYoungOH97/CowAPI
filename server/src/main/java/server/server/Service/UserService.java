package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.*;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;
import server.server.Domain.ResposneDto.OAuthUserResponseDto;
import server.server.Jwt.TokenConverter;
import server.server.Jwt.TokenProvider;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UsersRepository usersRepository;
    private final TokenProvider tokenProvider;
    private final TokenConverter tokenConverter;

    @Value(value = "${spring.security.oauth2.client.provider.naver.user-info-uri}")
    String userInfoUri;

    @Value(value = "${AWS.ip}")
    String requestIp;

    public UsersDto signUp(UsersDto userDto) {
        // 회원가입 형식
        if(userDto.getEmail().equals("")) throw new ResponseStatusException(CONFLICT, "이메일을 입력해 주세요");
        if(userDto.getPassword().equals("")) throw new ResponseStatusException(CONFLICT, "비밀번호를 입력해 주세요");

        // 이미 가입되어 있는 사용자
        if(usersRepository.existsByEmail(userDto.getEmail())) throw new ResponseStatusException(CONFLICT, "이미 가입되어 있는 유저입니다");

        // 사용자 초기화
        userDto.setCreatedUser();
        
        // 사용자 저장 후 로그인
        return signIn(UsersDto.of(usersRepository.save(UsersDto.toEntity(userDto))));
    }

    public UsersDto signIn(UsersDto userDto) {
        // 회원가입 형식
        if(userDto.getEmail().equals("")) throw new ResponseStatusException(CONFLICT, "이메일을 입력해 주세요");
        if(userDto.getPassword().equals("")) throw new ResponseStatusException(CONFLICT, "비밀번호를 입력해 주세요");

        // 존재 하지 않는 사용자
        Users foundUser = usersRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        
        // 탈퇴한 사용자
        if(foundUser.getIsDeleted()) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.");

        // 비밀번호가 틀렸습니다.
        if(!foundUser.getPassword().equals(userDto.getPassword())) throw new ResponseStatusException(BAD_REQUEST, "비밀번호가 틀렸습니다.");

        // 역할 부여
        SimpleGrantedAuthority role =  (foundUser.getIsAdmin()) ? new SimpleGrantedAuthority("ROLE_ADMIN") : new SimpleGrantedAuthority("ROLE_USER");

        // 토큰 발행 (로그인)
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), "", Collections.singleton(role));
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        UsersDto usersDto = UsersDto.of(foundUser);
        usersDto.setUserToken(tokenDto);

        return usersDto;
    }

    public UsersDto readMypage(TokenDto userToken) {
        // 사용자 찾기
        Users user  = tokenConverter.getUser(userToken);
        return UsersDto.of(user);
    }

    public UsersDto reissuance(TokenDto userToken) {
        // 사용자 찾기
        Users user  = tokenConverter.getUser(userToken);
        
        // 비밀키 재발행
        String secretKey = new BigInteger(130, new SecureRandom()).toString(32);
        UsersDto usersDto = UsersDto.builder().secretKey(secretKey).build();
        
        // 업데이트
        usersRepository.updateUser(user, usersDto);

        return UsersDto.of(user);
    }

    public void updateUser(TokenDto userToken, UsersDto usersDto) {
        Users foundUser  = tokenConverter.getUser(userToken);
        usersRepository.updateUser(foundUser, usersDto);
    }

    public void deleteUser(TokenDto userToken) {
        Users foundUser  = tokenConverter.getUser(userToken);
        usersRepository.deleteUser(foundUser);
    }



    public OAuthUserResponseDto OAuthUser(OAuthUserToken oAuthUserToken) {
        // Resource Server 로 요청할 WebClient
        WebClient client = WebClient.create(requestIp);

        // User Info 요청 하고 받기
        OAuthUserInfoDto userInfo = client.post()
                .uri(userInfoUri)
                .header(HttpHeaders.AUTHORIZATION, oAuthUserToken.getUserToken())
                .retrieve()
                .bodyToMono(OAuthUserInfoDto.class)
                .block();

        if(userInfo == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Resource Service Error!");

        String email = userInfo.getResponse().getEmail();

        UsersDto usersDto = UsersDto.builder().email(email).build();

        // OAuth 최초 가입 사용자라면
        if(!usersRepository.existsByEmail(email)) {
            usersDto.setPassword(email.substring(10));
            usersDto.setCreatedUser();
            usersRepository.save(UsersDto.toEntity(usersDto));
        }
        
        // 로그인
        Users foundUser = usersRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        UsersDto usersDto1 = signIn(UsersDto.of(foundUser));

        return OAuthUserResponseDto.builder()
                .authorization(usersDto1.getUserToken().getAccessToken())
                .isAdmin(usersDto1.getIsAdmin())
                .email(usersDto1.getEmail())
                .build();
    }
}
