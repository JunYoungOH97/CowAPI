package server.server.Redis;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import server.server.Domain.Entity.UserState;

@Service
@NoArgsConstructor
public class StateRedisService {
    @Autowired
    private RedisTemplate<String, UserState> stateRedisRepository;

    public void save(UserState state) {
        ValueOperations<String, UserState> valueOperations = stateRedisRepository.opsForValue();
        valueOperations.set(state.getState(), state);
    }

    public UserState get(UserState state) {
        ValueOperations<String, UserState> valueOperations = stateRedisRepository.opsForValue();
        try {
            return valueOperations.get(state.getState());

        } catch (NullPointerException e) {
            return null;
        }
    }

    public void delete(UserState state) {
        ValueOperations<String, UserState> valueOperations = stateRedisRepository.opsForValue();
        state.setValid(false);
        valueOperations.set(state.getState(), state);
    }
}
