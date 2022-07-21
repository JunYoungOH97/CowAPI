package server.server.Service;

import com.slack.api.util.http.listener.HttpResponseListener;
import io.lettuce.core.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.server.Domain.Entity.Dashboard;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Redis.DashboardRedisService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private static final Long refreshTime = 2L;
    private final DashboardRedisService dashboardRedisService;
    private final AiPageService aiPageService;
    private final DateConverterComponent dateConverterComponent = new DateConverterComponent();

    public Flux<ServerSentEvent<DashboardResponseDto>> publish(HttpServletRequest request) {
        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardResponseDto>builder()
                        .id(request.getHeader("X-FORWARDED-FOR"))
                        .event("dashboard")
                        .data(DashboardResponseDto.builder()
                                .todayUser(dashboardRedisService.getDashboard().getTodayUser())
                                .totalUser(dashboardRedisService.getDashboard().getTotalUser())
                                .updatedAt(dateConverterComponent.DateToResponse(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))))
                                .aiList(aiPageService.aiListPage().toResponse())
                                .build())
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

    public void visitUser() {
        Dashboard dashboard = dashboardRedisService.getDashboard();
        dashboard.setTotalUser(dashboard.getTotalUser() + 1L);
        dashboard.setTodayUser(dashboard.getTodayUser() + 1L);
        dashboardRedisService.update(dashboard);
    }

    public DashboardResponseDto firstDashboard() {
        visitUser();
        return DashboardResponseDto.builder()
                .todayUser(dashboardRedisService.getDashboard().getTodayUser())
                .totalUser(dashboardRedisService.getDashboard().getTotalUser())
                .updatedAt(dateConverterComponent.DateToResponse(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))))
                .aiList(aiPageService.aiListPage().toResponse())
                .build();
    }
}
