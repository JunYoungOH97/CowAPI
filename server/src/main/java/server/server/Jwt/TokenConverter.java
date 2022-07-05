package server.server.Jwt;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.TokenDto;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@Component
@RequiredArgsConstructor
public class TokenConverter {
    private final UsersRepository usersRepository;
    private final TokenProvider tokenProvider;

    public static final String BEARER_PREFIX = "Bearer ";

    public String getToken(TokenDto userToken) {
        if (userToken.getAccessToken() != null && userToken.getAccessToken().startsWith(BEARER_PREFIX)) {
            return userToken.getAccessToken().substring(7);
        }
        return null;
    }

    public Users getUser(TokenDto userToken) {
        return usersRepository.findByEmail(tokenProvider.getUserEmailFromToken(getToken(userToken))).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
    }


}
