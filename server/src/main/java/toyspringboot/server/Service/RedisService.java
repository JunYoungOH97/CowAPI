package toyspringboot.server.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import toyspringboot.server.Domain.Entity.OAuthState;

@Service
@NoArgsConstructor
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveOAuthState(OAuthState oAuthState) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(oAuthState.getEmail(), oAuthState.getState());
    }

    public OAuthState getOAuthState(String email) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String state = valueOperations.get(email);
        return OAuthState.builder().email(email).state(state).build();
    }
}
