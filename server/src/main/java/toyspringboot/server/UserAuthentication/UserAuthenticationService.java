package toyspringboot.server.UserAuthentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.UserRepository;

import java.util.Collections;

// 유저 정보를 가져오는 Service
@Repository
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자 입니다."));
        if(user.getAdmin()) user.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        else user.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }
}
