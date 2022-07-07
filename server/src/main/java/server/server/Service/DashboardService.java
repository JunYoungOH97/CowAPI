package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import server.server.Domain.Entity.Dashboard;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Redis.DashboardRedisService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private static final Long refreshTime = 2L;
    private final DashboardRedisService dashboardRedisService;
    private final AiPageService aiPageService;

    public Flux<ServerSentEvent<DashboardResponseDto>> publish() {
        AiListResponseDto aiListResponseDto = aiPageService.aiListPage().toResponse();

        Dashboard dashboard = dashboardRedisService.getDashboard();

        DashboardResponseDto dashboardResponseDto = DashboardResponseDto.builder()
                .todayUser(dashboard.getTodayUser())
                .totalUser(dashboard.getTotalUser())
                .updatedAt(dashboard.getUpdatedAt())
                .aiList(aiListResponseDto)
                .build();

        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardResponseDto>builder()
                        .id("/dashboard")
                        .event("dashboard")
                        .data(dashboardResponseDto)
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());

    }
}
