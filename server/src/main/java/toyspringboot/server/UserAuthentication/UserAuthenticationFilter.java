package toyspringboot.server.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserAuthenticationConverter userAuthenticationConverter;
    private final UserAuthenticationManager userAuthenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // UserConverter 를 통해 http request -> authentication
        UserAuthentication authentication = (UserAuthentication) userAuthenticationConverter.convert((HttpServletRequest) servletRequest);
        
        // UserManager 를 통해 만료 확인
        // UserManager 를 통해 권한 및 jwt 얻기
        if(userAuthenticationManager.isCredentialsNonExpired(authentication)) authentication = (UserAuthentication) userAuthenticationManager.authenticate(authentication);
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "만료된 JWT 토큰 입니다.");

        // SecurityContext 에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음 Filter 를 실행하기 위한 코드. 마지막 필터라면 필터 실행 후 리소스를 반환한다.
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
