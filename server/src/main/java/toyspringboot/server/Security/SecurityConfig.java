package toyspringboot.server.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import toyspringboot.server.UserAuthentication.UserAuthenticationConfig;
import toyspringboot.server.UserAuthentication.UserAuthenticationManager;
import toyspringboot.server.UserAuthentication.UserAuthenticationProvider;
import toyspringboot.server.UserAuthentication.UserAuthenticationService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserAuthenticationService userAuthenticationService;
    private final UserAuthenticationProvider userAuthenticationProvider;
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
//        httpSecurity.userDetailsService(userSecurity);
//        initSetting(httpSecurity);
////        swagger(httpSecurity);
//        noticeSecurity(httpSecurity);

        httpSecurity
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrf().disable()
                .exceptionHandling()

                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/v1/notices/notice").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/v1/notices/notice").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/v1/notices/notice/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/api/v1/notices/notice/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                    .anyRequest().permitAll()

                .and()
                .apply(new UserAuthenticationConfig(userAuthenticationProvider))

                .and()
                .authenticationManager(userAuthenticationManager);


    }

    private void initSetting(HttpSecurity httpSecurity) throws Exception {
//        // rest api 이므로 기본설정 안함. 기본설정은 비인증 시 로그인 폼 화면으로 리다이렉트 된다.
//        httpSecurity.httpBasic().disable();
//
//        // rest api 이므로 csrf 보안이 필요 없음. disable
//        httpSecurity.cors().and();
//        httpSecurity.csrf().disable();

//        httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//        httpSecurity.cors().disable();

    }

    private void noticeSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/notices/notice").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/notices/notice").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/notices/notice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/notices/notice/**").permitAll()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**")
                .permitAll();
//                .antMatchers("/api/v1/notices/notice/**").permitAll();
    }

    // Swagger 문서 권한 해제
    private void swagger(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**")
                .permitAll();
    }
}
