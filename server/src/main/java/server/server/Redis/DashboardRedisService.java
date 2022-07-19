package server.server.Redis;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Entity.AiRedis;
import server.server.Domain.Entity.Dashboard;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@NoArgsConstructor
public class DashboardRedisService {
    @Autowired
    private RedisTemplate<String, Dashboard> dashboardRedisRepository;

    public void save(Dashboard dashboard) {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        valueOperations.set(dashboard.getId(), dashboard);
    }

    public Dashboard getDashboard() {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        try {
            return valueOperations.get("dashboard");

        } catch (NullPointerException e) {
            return null;
        }
    }

    public void update(Dashboard dashboard) {
        Dashboard dashboard1 = getDashboard();

        if(dashboard.getTodayUser() != null) dashboard1.setTodayUser(dashboard.getTodayUser());
        if(dashboard.getTotalUser() != null) dashboard1.setTotalUser(dashboard.getTotalUser());

        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dashboard1.setUpdatedAt(now);
        save(dashboard1);
    }
}
