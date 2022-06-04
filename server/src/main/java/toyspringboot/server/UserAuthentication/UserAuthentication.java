package toyspringboot.server.UserAuthentication;

import lombok.Builder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import toyspringboot.server.Domain.Entity.User;

import java.util.Collection;

@Builder
public class UserAuthentication implements Authentication {
    private User user;
    private Collection<SimpleGrantedAuthority> authorities;
    boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
