package toyspringboot.server.UserAuthentication;

import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


// 유저가 권한이 있는지 확인하는 필터
@Component
@NoArgsConstructor
public class UserAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 인가가 되지 않은 접근을 하려고 할 때
        if(authentication.isAuthenticated()) throw new DisabledException("인가가 되지 않은 사용자 입니다.");
//        else if(authentication.getCredentials()) return new BadCredentialsException("BadCredentialsException");
        return authentication;
    }
}
