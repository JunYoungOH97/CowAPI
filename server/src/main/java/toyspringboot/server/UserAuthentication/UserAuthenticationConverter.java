package toyspringboot.server.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import toyspringboot.server.Domain.Entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserAuthenticationConverter implements AuthenticationConverter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken)) {
            User user = (User) userAuthenticationService.loadUserByUsername(bearerToken);

            return UserAuthentication.builder()
                    .user(user)
                    .build();
        }

        return null;
    }
}
