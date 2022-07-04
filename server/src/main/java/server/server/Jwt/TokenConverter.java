package server.server.Jwt;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import server.server.Domain.Dto.TokenDto;


@Slf4j
@Component
@NoArgsConstructor
public class TokenConverter {
    public static final String BEARER_PREFIX = "Bearer ";

    public String getEmail(TokenDto userToken) {
        if (userToken.getAccessToken() != null && userToken.getAccessToken().startsWith(BEARER_PREFIX)) {
            return userToken.getAccessToken().substring(7);
        }
        return null;
    }
}
