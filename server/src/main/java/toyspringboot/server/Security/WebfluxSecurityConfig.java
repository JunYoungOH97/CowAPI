//package toyspringboot.server.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@EnableWebFluxSecurity
//@Configuration
//public class WebfluxSecurityConfig {
//    @Bean
//    public SecurityWebFilterChain springWebfluxSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
//        serverHttpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .cors().disable()
//                .exceptionHandling()
//
//                .and()
//                .authorizeExchange(exchanges -> exchanges
//                                        .anyExchange().authenticated()
//                                    );
//
//        return serverHttpSecurity.build();
//    }
//}
