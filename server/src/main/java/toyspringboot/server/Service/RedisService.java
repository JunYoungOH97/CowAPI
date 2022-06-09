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
    private RedisTemplate<String, Boolean> redisTemplate;

    public boolean saveOAuthState(String state) {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(state, Boolean.TRUE);
        return true;
    }

    public boolean getOAuthState(String state) {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        return Boolean.TRUE.equals(valueOperations.get(state));
    }


    public boolean deleteOAuthState(String state) {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(state, Boolean.FALSE);
        return true;
    }
}
