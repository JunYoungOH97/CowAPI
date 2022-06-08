package toyspringboot.server.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void saveToRedis(String email, String state) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(email, state);
    }

    public String get(String email) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        return valueOperations.get(email);
    }
}
