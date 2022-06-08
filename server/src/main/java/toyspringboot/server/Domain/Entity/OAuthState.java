package toyspringboot.server.Domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@RedisHash(value = "state", timeToLive = 30)
public class OAuthState {
    @Id
    private String email;
    private String state;

    public OAuthState(String email, String state) {
        this.email = email;
        this.state = state;
    }
}
