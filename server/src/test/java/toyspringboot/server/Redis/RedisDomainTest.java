package toyspringboot.server.Redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import toyspringboot.server.Domain.Entity.OAuthState;

@SpringBootTest
public class RedisDomainTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("[Domain] Redis 생성")
    public void saveToRedis() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        OAuthState oAuthState = new OAuthState("test", "state");
        valueOperations.set("test", "hello");
    }
}
