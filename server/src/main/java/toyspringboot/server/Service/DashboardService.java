package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Domain.Entity.Dashboard;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.apache.tomcat.jni.Time.sleep;


@Service
@RequiredArgsConstructor
public class DashboardService {
    private final RedisService redisService;
    private static final Long refreshTime = 2L;

    public Flux<ServerSentEvent<DashboardDto>> sse() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        // 대시보드를 가져온다.
        DashboardDto dashboardDto = getDashboard();
        if(dashboardDto == null) saveDashboard(DashboardDto.builder().id("dashboard").updatedTime(now).build());

        // 대시보드를 갱신한다.
        DashboardDto updatedDashboard = DashboardDto.builder()
                .todayUser(1L)
                .totalUser(1L)
                .responseTime(0.1)
                .todayTps(0.1)
                .updatedTime(now)
                .build();

        updateDashboard(updatedDashboard);

        // 보낸다.
        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardDto> builder()
                        .id("dashboard")
                        .event("periodic-event")
                        .data(getDashboard())
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

    public boolean saveDashboard(DashboardDto dashboardDto) {
        return redisService.saveDashboard(DashboardDto.toEntity(dashboardDto));
    }

    public DashboardDto getDashboard() {
        return DashboardDto.of(redisService.getDashboard());
    }

    public boolean updateDashboard(DashboardDto dashboardDto) {
        return redisService.updateDashboard(DashboardDto.toEntity(dashboardDto));
    }
}
