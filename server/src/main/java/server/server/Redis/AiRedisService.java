package server.server.Redis;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import server.server.Domain.Entity.AiRedis;

@Service
@NoArgsConstructor
public class AiRedisService {
    @Autowired
    private RedisTemplate<String, AiRedis> aiRedisTemplate;

    public void save(AiRedis ai) {
        ValueOperations<String, AiRedis> valueOperations = aiRedisTemplate.opsForValue();
        valueOperations.set(ai.getName(), ai);
    }

    public AiRedis read(String name) {
        ValueOperations<String, AiRedis> valueOperations = aiRedisTemplate.opsForValue();
        try {
            return valueOperations.get(name);

        } catch (NullPointerException e) {
            return null;
        }
    }
}
