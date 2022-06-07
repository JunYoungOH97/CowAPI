package toyspringboot.server.UserAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import toyspringboot.server.Domain.Entity.User;

import javax.servlet.http.HttpServletRequest;

@Component
@NoArgsConstructor
public class UserAuthenticationConverter implements AuthenticationConverter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String secretKey = "secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey";

    @Override
    public Authentication convert(HttpServletRequest request) {

        // bearer ... -> header token
        String headerToken = getTokenFromRequest(request);

        // header token -> user email
        String userEmail = getUserEmailFromToken(headerToken);

        // generate default Token
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userEmail, headerToken);

        // new user authentication
        return UserAuthentication.builder()
                .userToken(userToken)
                .build();
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String headerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(headerToken) && headerToken.startsWith(BEARER_PREFIX)) {
            return headerToken.substring(7);
        }
        return null;
    }

    public String getUserEmailFromToken(String headerToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(headerToken)
                .getBody();

        return claims.getSubject();
    }

    public String getTempUserEmail(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }
        return null;
    }
}
