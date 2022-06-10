package toyspringboot.server.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
=======
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Entity.Dashboard;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======

    public boolean saveDashboard(Dashboard dashboard) {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        valueOperations.set("dashboard", dashboard);
        return true;
    }

    public Dashboard getDashboard() {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        return valueOperations.get("dashboard");
    }

    public boolean updateDashboard(Dashboard dashboard) {
        Dashboard foundDashboard = getDashboard();

        if(dashboard.getUpdatedTime() != null && foundDashboard.getUpdatedTime().toLocalDateTime().isAfter(dashboard.getUpdatedTime().toLocalDateTime())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업데이트 시간이 누락되었습니다.");
        if(dashboard.getUpdatedTime() != null) foundDashboard.setUpdatedTime(dashboard.getUpdatedTime());
        if(dashboard.getTotalUser() != null) foundDashboard.setTodayUser(dashboard.getTotalUser());
        if(dashboard.getTodayTps() != null) foundDashboard.setTodayTps(dashboard.getTodayTps());
        if(dashboard.getResponseTime() != null) foundDashboard.setResponseTime(dashboard.getResponseTime());
        if(dashboard.getTodayUser() != null) foundDashboard.setTodayUser(dashboard.getTodayUser());

        saveDashboard(foundDashboard);

        return true;
    }

    public boolean existDashboard() {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        return valueOperations.get("dashboard") != null;
    }
>>>>>>> Stashed changes
}
