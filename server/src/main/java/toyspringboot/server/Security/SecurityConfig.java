//package toyspringboot.server.Security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import toyspringboot.server.UserAuthentication.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserAuthenticationService userAuthenticationService;
//    private final UserAuthenticationConverter userAuthenticationConverter;
//    private final UserAuthenticationManager userAuthenticationManager;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    // UserDetailsService 구현체
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userAuthenticationService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // 기본 설정
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring()
//                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/favicon.ico")
//                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**")
//                .antMatchers("/users/oauth", "/oauth/naver**", "/users/signup**", "/users/login**")
//                .antMatchers("/dashboard")
//                .antMatchers("/**/ai/**")
//                .antMatchers("/QnAs/**")
//                .antMatchers("/**/image");
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors().configurationSource(corsConfigurationSource())
//                .and()
//
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/favicon.ico").permitAll()
//                .antMatchers("/users/oauth", "/oauth/naver**", "/users/signup", "/users/login").permitAll()
//                .antMatchers("/dashboard").permitAll()
//                .antMatchers("/**/ai/**").permitAll()
//                .antMatchers("/QnAs/**").permitAll()
//                .antMatchers("/**/image").permitAll()
//
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                .and()
//                .apply(new UserAuthenticationConfig(userAuthenticationConverter, userAuthenticationManager))
//
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated();
//    }
//
//    protected CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns((List.of("http://localhost:3000", "http://localhost:8080"))); // 허용할 URL
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE")); // 허용할 Http Method
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); // 허용할 Header
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // add mapping
//        return source;
//    }
//}
