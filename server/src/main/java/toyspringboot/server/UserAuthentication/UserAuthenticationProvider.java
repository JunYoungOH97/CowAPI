package toyspringboot.server.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import toyspringboot.server.Domain.Entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

// 유저의 권한을 제공하는 필터
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserAuthenticationConverter userAuthenticationConverter;


    public Authentication provideRole(HttpServletRequest httpServletRequest) {
        Authentication userAuthentication = userAuthenticationConverter.convert(httpServletRequest);
        Authentication authentication =  authenticate(userAuthentication);
        if(supports(authentication.getClass())) return authentication;
        else return null;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return (Authentication) UserAuthentication.builder()
                .authorities((Collection<SimpleGrantedAuthority>) ((User) authentication.getDetails()).getAuthorities())
                .authenticated(true)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
