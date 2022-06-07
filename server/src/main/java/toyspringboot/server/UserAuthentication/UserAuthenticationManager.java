package toyspringboot.server.UserAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import toyspringboot.server.Domain.Entity.User;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
//
//// 유저의 권한을 제공하는 필터
@Component
@RequiredArgsConstructor
public class UserAuthenticationManager implements AuthenticationProvider {
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthentication userAuthentication = (UserAuthentication) authentication;
        userAuthenticationProvider.setUserAuthenticationToken(userAuthentication);
        userAuthenticationProvider.setUserAuthenticationRole(userAuthentication);
        userAuthenticationProvider.setUserAuthenticationUserDetail(userAuthentication);

        System.out.println("Email : " + authentication.getName() + " authentication = " + authentication.getCredentials());
        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthentication.class.isAssignableFrom(authentication);
    }
}
