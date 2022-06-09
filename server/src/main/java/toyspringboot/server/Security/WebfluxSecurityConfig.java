package toyspringboot.server.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class WebfluxSecurityConfig {


    @Bean
    public SecurityWebFilterChain springWebfluxSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        serverHttpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .exceptionHandling()

                .and()
                .authorizeExchange()
                .pathMatchers("/**").permitAll();

        return serverHttpSecurity.build();
    }
}
