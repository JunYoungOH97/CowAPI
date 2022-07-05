package server.server.Redis;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import server.server.Domain.Entity.Dashboard;

@Service
@NoArgsConstructor
public class DashboardRedisService {
    @Autowired
    private RedisTemplate<String, Dashboard> dashboardRedisRepository;

    public void save(Dashboard dashboard) {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        valueOperations.set(dashboard.getId(), dashboard);
    }
}
