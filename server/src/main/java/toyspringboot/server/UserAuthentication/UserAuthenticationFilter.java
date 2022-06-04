package toyspringboot.server.UserAuthentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import toyspringboot.server.Domain.Entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserAuthenticationProvider userAuthenticationProvider;

    public UserAuthenticationFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // UserProvider 를 통해 역할 부여
        Authentication authentication = userAuthenticationProvider.provideRole(httpServletRequest);

        // 유효성 검증을 하고 정상이면 SecurityContext 에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음 Filter 를 실행하기 위한 코드. 마지막 필터라면 필터 실행 후 리소스를 반환한다.
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
