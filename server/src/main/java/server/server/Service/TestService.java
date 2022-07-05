package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.Domain.Entity.AiRedis;
import server.server.Domain.Entity.Dashboard;
import server.server.Redis.AiRedisService;
import server.server.Redis.DashboardRedisService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final AiRedisService aiRedisService;
    private final DashboardRedisService dashboardRedisService;

    public void saveVgg() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        AiRedis ai = AiRedis.builder()
                .name("vgg")
                .field("vision")
                .responseTime(0.0)
                .accuracy(0.0)
                .requestURI("/test")
                .method("method")
                .req("req")
                .res("res")
                .isDeleted(false)
                .updater("Directly")
                .updatedAt(now)
                .createdAt(now)
                .build();

        aiRedisService.save(ai);
    }

    public void saveLang() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        AiRedis ai = AiRedis.builder()
                .name("Lang")
                .field("nlp")
                .responseTime(0.0)
                .accuracy(0.0)
                .requestURI("/test")
                .method("method")
                .req("req")
                .res("res")
                .isDeleted(false)
                .updater("Directly")
                .updatedAt(now)
                .createdAt(now)
                .build();

        aiRedisService.save(ai);
    }

    public void saveDashboard() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Dashboard dashboard = Dashboard.builder()
                .id("dashboard")
                .totalUser(1L)
                .todayUser(1L)
                .updatedAt(now)
                .build();

        dashboardRedisService.save(dashboard);
    }
}
