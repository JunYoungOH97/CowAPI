package toyspringboot.server.Dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Domain.Entity.Dashboard;
import toyspringboot.server.Service.DashboardService;
import toyspringboot.server.Service.RedisService;
import toyspringboot.server.TestConfig.ServiceTest;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DashBoardServiceTest extends ServiceTest {
    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private RedisService redisService;

    private String id;

    @BeforeEach
    void init() {
        id = "dashboard";
    }

    @Test
    @DisplayName("[Service] 대시보드 저장 테스트")
    public void save() {
        // given
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        when(redisService.getDashboard()).thenReturn(dashboard);

        boolean dashboardDto = dashboardService.saveDashboard(DashboardDto.of(dashboard));

        // then
        assertTrue(dashboardDto);
        verify(redisService).saveDashboard(any(Dashboard.class));
    }

    @Test
    @DisplayName("[Service] 대시보드 조회 테스트")
    public void read() {
        // given
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        dashboardService.saveDashboard(DashboardDto.of(dashboard));

        // when
        when(redisService.getDashboard()).thenReturn(dashboard);

        DashboardDto dashboardDto = dashboardService.getDashboard();

        // then
        assertEquals(dashboard.getTodayUser(), dashboardDto.getTodayUser());
        verify(redisService).getDashboard();
    }

    @Test
    @DisplayName("[Service] 대시보드 수정 테스트")
    public void update() {
        // given
        Dashboard dashboard = Dashboard.builder()
                .id(id)
                .totalUser(100L)
                .todayUser(1L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .updatedTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        dashboardService.saveDashboard(DashboardDto.of(dashboard));

        Dashboard target = Dashboard.builder()
                .id(id)
                .todayUser(200L)
                .build();

        // when
        when(redisService.updateDashboard(any(Dashboard.class))).thenReturn(true);

        boolean d = dashboardService.updateDashboard(DashboardDto.of(target));

        // then
        verify(redisService).updateDashboard(any(Dashboard.class));
    }
}
