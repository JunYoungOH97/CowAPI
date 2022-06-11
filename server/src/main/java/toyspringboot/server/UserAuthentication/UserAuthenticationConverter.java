package toyspringboot.server.UserAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import toyspringboot.server.Domain.Dto.TokenDto;
import toyspringboot.server.Domain.Entity.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;

@Component
@NoArgsConstructor
public class UserAuthenticationConverter implements AuthenticationConverter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Value(value = "${jwt.secret}")
    String secretKey;

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

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String getUserEmailFromToken(String headerToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(headerToken)
                .getBody();

        return claims.getSubject();
    }

    public String getUserEmailFromRequestHeader(String userToken) {
        if (StringUtils.hasText(userToken) && userToken.startsWith(BEARER_PREFIX)) {
            userToken = userToken.substring(7);
            return getUserEmailFromToken(userToken);
        }
        else {
            return null;
        }
    }
}
