package toyspringboot.server.UserAuthentication;

import lombok.Builder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
public class UserAuthentication implements Authentication {
    private UsernamePasswordAuthenticationToken userToken;

    public UsernamePasswordAuthenticationToken getUserToken() {
        return this.userToken;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.userToken = new UsernamePasswordAuthenticationToken(this.userToken.getPrincipal(), this.userToken.getCredentials(), authorities);
    }

    public void setCredential(String jwtToken) {
        this.userToken = new UsernamePasswordAuthenticationToken(this.userToken.getPrincipal(), jwtToken, this.userToken.getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userToken.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return this.userToken.getCredentials();
    }

    @Override
    public Object getDetails() {
        return this.userToken.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return this.userToken.getPrincipal();
    }

    @Override
    public boolean isAuthenticated() {
        return this.userToken.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.userToken.setAuthenticated(isAuthenticated);
    }

    @Override
    public String getName() {
        return this.userToken.getName();
    }
}
