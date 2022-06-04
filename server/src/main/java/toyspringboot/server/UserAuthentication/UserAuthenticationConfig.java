package toyspringboot.server.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class UserAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) {
        UserAuthenticationFilter userAuthenticationFilter = new UserAuthenticationFilter(userAuthenticationProvider);
        httpSecurity.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
