package toyspringboot.server.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "state", timeToLive = 30)
public class OAuthState implements Serializable {
    @Id
    private String email;
    private String state;
}
