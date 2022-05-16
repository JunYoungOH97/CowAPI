//package toyspringboot.server.JWT;
//
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import toyspringboot.server.Domain.Entity.User;
//
//import java.security.Key;
//import java.util.Date;
//
//@Slf4j
//@Component
//public class TokenProvider {
//    private final Long validTime;
//    private final Key key;
//
//    public TokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.token-validity-in-seconds}") Long validTime) {
//        this.validTime = validTime;
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    /*
//       토큰 생성 메서드
//  */
//    public String createToken(Authentication authentication) {
//        Date validAccessDate = new Date((new Date()).getTime() + validTime);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validAccessDate)
//                .compact();
//    }
//
//
//    /*
//        Token에 담겨있는 정보를 이용해 Authentication 객체를 반환하는 메서드
//    */
//    public String getEmail(String token) {
//        return parsing(token).getSubject();
//    }
//
//    public Claims parsing(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public Authentication getAuthentication(String token) {
//        String email = getEmail(token);
//
//        User user = User.builder()
//                .email(email)
//                .build();
//
//        return new UsernamePasswordAuthenticationToken(user, token);
//    }
//
//    /*
//        토큰을 파싱하고 발생하는 예외를 처리, 문제가 있을 경우 false 반환
//    */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            log.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            log.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
//
//}
