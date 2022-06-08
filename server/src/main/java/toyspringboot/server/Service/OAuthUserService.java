package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import toyspringboot.server.Domain.Dto.RedirectURIDto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.OAuthState;
import toyspringboot.server.Domain.Repository.OAuthStateRepository;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class OAuthUserService {
    private final OAuthStateRepository oAuthStateRepository;

    @Value(value = "${spring.security.oauth2.client.registration.naver.redirect-uri}")
    String redirectURI;

    @Value(value = "${spring.security.oauth2.client.registration.naver.clientId}")
    String clientId;

    @Value(value = "${spring.security.oauth2.client.registration.naver.clientSecret}")
    String clientSecret;

    @Value(value = "${spring.security.oauth2.client.registration.naver.clientScope}")
    String clientScope;

    @Value(value = "${oauth.callBackURL}")
    String callBackURL;

    public RedirectURIDto getRedirectURI(UserDto userDto, String resourceServer) {
        String state = generateState();

        OAuthState oAuthState = new OAuthState(userDto.getEmail(), state);
        oAuthStateRepository.save(oAuthState);

        return RedirectURIDto.builder()
                .redirectURI(
                        redirectURI + "?response_type=code" + "&client_id=" + clientId + "&state=" + oAuthState.getState() + "&redirect_uri=" + callBackURL + resourceServer
                )
                .build();

    }

    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
