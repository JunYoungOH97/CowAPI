package toyspringboot.server.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import toyspringboot.server.UserAuthentication.UserAuthenticationConfig;
import toyspringboot.server.UserAuthentication.UserAuthenticationConverter;
import toyspringboot.server.UserAuthentication.UserAuthenticationManager;
import toyspringboot.server.UserAuthentication.UserAuthenticationService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserAuthenticationService userAuthenticationService;
    private final UserAuthenticationConverter userAuthenticationConverter;
    private final UserAuthenticationManager userAuthenticationManager;

    // UserDetailsService 구현체
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticationService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 기본 설정
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()
                .exceptionHandling()

//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/v1/notices/notice").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/v1/notices/notice").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/notices/notice/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/api/v1/notices/notice/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                    .antMatchers("**users**").permitAll()
                    .anyRequest().authenticated()


                .and()
                    .apply(new UserAuthenticationConfig(userAuthenticationConverter, userAuthenticationManager));



    }
}
