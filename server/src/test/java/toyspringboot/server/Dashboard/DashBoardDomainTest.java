package toyspringboot.server.Dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Entity.Dashboard;
import toyspringboot.server.Service.RedisService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
public class DashBoardDomainTest {
    @Autowired
    private RedisService redisService;

    private String id;

    @BeforeEach
    void init() {
        id = "dashboard";
    }

    @Test
    @DisplayName("[Domain] 대쉬보드 정보 저장 테스트")
    public void saveTest() {
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .build();

        redisService.saveDashboard(dashboard);
        Dashboard found = redisService.getDashboard();

        assertEquals(dashboard.getId(), found.getId());
        assertEquals(dashboard.getResponseTime(), found.getResponseTime());
        assertEquals(dashboard.getTodayTps(), found.getTodayTps());
        assertEquals(dashboard.getTodayUser(), found.getTodayUser());
        assertEquals(dashboard.getTotalUser(), found.getTotalUser());
    }

    @Test
    @DisplayName("[Domain] 대쉬보드 정보 조회 테스트")
    public void readTest() {
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .build();

        redisService.saveDashboard(dashboard);

        assertEquals(id, redisService.getDashboard().getId());
    }

    @Test
    @DisplayName("[Domain] 대쉬보드 정보 수정 테스트")
    public void updateTest() {
        // given
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        redisService.saveDashboard(dashboard);

        Dashboard target = Dashboard.builder()
                .id(id)
                .todayUser(200L)
                .build();

        // when
        redisService.updateDashboard(target);

        // then
        assertEquals(redisService.getDashboard().getTodayUser(), target.getTodayUser());
    }
}
