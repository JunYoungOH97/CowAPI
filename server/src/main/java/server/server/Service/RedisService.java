package server.server.Service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.DashboardDto;
import server.server.Domain.Entity.Dashboard;

@Service
@NoArgsConstructor
public class RedisService {
    @Autowired
    private RedisTemplate<String, Dashboard> dashboardRedisRepository;

    public void saveDashboard(Dashboard dashboard) {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        valueOperations.set("dashboard", dashboard);
    }

    public Dashboard getDashboard() {
        ValueOperations<String, Dashboard> valueOperations = dashboardRedisRepository.opsForValue();
        try {
            return valueOperations.get("dashboard");

        } catch (NullPointerException e) {
            return  DashboardDto.toEntity(DashboardDto.getInitDashboard());
        }
    }

    public void updateDashboard(Dashboard dashboard) {
        Dashboard foundDashboard = getDashboard();

        foundDashboard.setId("dashboard");
        if(dashboard.getTodayUser() != null) foundDashboard.setTodayUser(dashboard.getTodayUser());
        if(dashboard.getTotalUser() != null) foundDashboard.setTotalUser(dashboard.getTotalUser());
        if(dashboard.getAccuracy() != null) foundDashboard.setAccuracy(dashboard.getAccuracy());
        if(dashboard.getResponseTime() != null) foundDashboard.setResponseTime(dashboard.getResponseTime());
        if(dashboard.getUpdatedAt() != null && foundDashboard.getUpdatedAt().toLocalDateTime().isAfter(dashboard.getUpdatedAt().toLocalDateTime())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업데이트 시간이 누락되었습니다.");
        if(dashboard.getUpdatedAt() != null) foundDashboard.setUpdatedAt(dashboard.getUpdatedAt());
        if(dashboard.getUseCnt() != null) foundDashboard.setUseCnt(dashboard.getUseCnt());

        saveDashboard(foundDashboard);
    }
}
