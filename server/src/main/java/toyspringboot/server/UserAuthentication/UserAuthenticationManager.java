package toyspringboot.server.UserAuthentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Entity.User;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Date;
//
//// 유저의 권한을 제공하는 필터
@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthenticationManager implements AuthenticationProvider {
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Value(value = "${jwt.secret}")
    String secretKey;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthentication userAuthentication = (UserAuthentication) authentication;
        userAuthenticationProvider.setUserAuthenticationToken(userAuthentication);
        userAuthenticationProvider.setUserAuthenticationRole(userAuthentication);
        userAuthenticationProvider.setUserAuthenticationUserDetail(userAuthentication);

        if(!validateToken(userAuthentication)) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "JWT 토큰 오류");
        if(!supports(userAuthentication.getClass())) throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not Support Object");
        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Authentication.class.isAssignableFrom(authentication);
    }

    public boolean validateToken(UserAuthentication userAuthentication) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(userAuthentication.getCredentials().toString());
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public boolean isCredentialsNonExpired(UserAuthentication userAuthentication) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(userAuthentication.getCredentials().toString());
            return true;

        } catch (ExpiredJwtException e) {
            return false;
        }
    }
}
