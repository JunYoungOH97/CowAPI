package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import server.server.Domain.Entity.Dashboard;
import server.server.Redis.DashboardRedisService;


@Service
@RequiredArgsConstructor
@EnableScheduling
public class TodayUserScheduler {
    private final DashboardRedisService dashboardRedisService;

    @Scheduled(cron = "0 0 0 * * *")
    public void initTodayUser() {
        Dashboard dashboard = dashboardRedisService.getDashboard();
        dashboard.setTodayUser(0L);
        dashboardRedisService.update(dashboard);
    }
}
