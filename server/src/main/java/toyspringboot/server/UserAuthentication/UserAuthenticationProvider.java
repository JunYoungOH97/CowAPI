package toyspringboot.server.UserAuthentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import toyspringboot.server.Domain.Entity.User;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;

    @Value(value = "${jwt.secret}")
    String secretKey;

    private final UserAuthenticationService userAuthenticationService;

    public void setUserAuthenticationUserDetail(UserAuthentication userAuthentication) {
        UserDetails userDetails = userAuthenticationService.loadUserByUsername(userAuthentication.getPrincipal().toString());
        userAuthentication.getUserToken().setDetails(userDetails);
    }

    public void setUserAuthenticationToken (UserAuthentication userAuthentication) {
        String jwtToken = getJwtToken(userAuthentication);
        userAuthentication.setCredential(jwtToken);
    }

    public void setUserAuthenticationRole (UserAuthentication userAuthentication) {
        UserDetails userDetails = userAuthenticationService.loadUserByUsername(userAuthentication.getPrincipal().toString());
        userAuthentication.setAuthorities(userDetails.getAuthorities());
    }

    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getJwtToken(UserAuthentication userAuthentication) {
        return generateToken(userAuthentication.getName());
    }

    public String generateToken(String userName) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(userName) // 사용자
                .setIssuedAt(now) // 현재 시간 기반으로 생성
                .setExpiration(expiryDate) // 만료 시간 세팅
                .signWith(getKey(), SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘, signature 에 들어갈 secret 값 세팅
                .compact();
    }
}
