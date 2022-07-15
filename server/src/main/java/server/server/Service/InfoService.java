package server.server.Service;

import com.google.gson.JsonObject;
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
public class InfoService {
    private final AiRedisService aiRedisService;
    private final DashboardRedisService dashboardRedisService;

    public void saveVgg() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        JsonObject req =new JsonObject();

        JsonObject header =new JsonObject();
        header.addProperty("content-type", "multipart/form-data");

        JsonObject body =new JsonObject();
        body.addProperty("images", "from-data");

        req.add("header", header);
        req.add("body", body);

        JsonObject res = new JsonObject();

        JsonObject header1 =new JsonObject();
        JsonObject body2 =new JsonObject();
        body2.addProperty("category", "String");
        body2.addProperty("accuracy", "Double");

        res.add("header", header1);
        res.add("body", body2);

        AiRedis ai = AiRedis.builder()
                .name("vgg")
                .field("vision")
                .useCnt(1L)
                .responseTime(0.1)
                .accuracy(1.0)
                .requestURI("/ai/VGG19?email={email}&secretKey={secretKey}")
                .method("POST")
                .req(req.toString())
                .res(res.toString())
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
                .useCnt(1L)
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
