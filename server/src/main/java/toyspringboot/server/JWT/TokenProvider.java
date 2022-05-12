package toyspringboot.server.JWT;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import toyspringboot.server.Domain.Entity.User;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final Logger logger = (Logger) LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.token-validity-in-seconds}")
    private static Long validTime;

    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

	 /*
		토큰 생성 메서드
   */
     public String createToken(Authentication authentication) {
         String authorities = authentication.getAuthorities().stream()
                 .map(GrantedAuthority::getAuthority)
                 .collect(Collectors.joining(","));

         Date validity = new Date((new Date()).getTime() + validTime);

         return Jwts.builder()
                 .setSubject(authentication.getName())
                 .claim(AUTHORITIES_KEY, authorities)
                 .signWith(key, SignatureAlgorithm.HS512)
                 .setExpiration(validity)
                 .compact();
     }


	 /*
		 Token에 담겨있는 정보를 이용해 Authentication 객체를 반환하는 메서드
	 */
     public Authentication getAuthentication(String token) {
         Claims claims = Jwts
                 .parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();

         Collection<? extends GrantedAuthority> authorities =
                 Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                         .map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toList());

         User principal = User.builder()
                 .email(claims.getSubject())
                 .build();

         return new UsernamePasswordAuthenticationToken(principal, token, authorities);
     }

	 /*
	 	토큰을 파싱하고 발생하는 예외를 처리, 문제가 있을 경우 false 반환
	 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
