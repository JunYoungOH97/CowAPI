package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import server.server.Domain.Entity.Dashboard;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Redis.DashboardRedisService;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private static final Long refreshTime = 60L * 5;
    private final DashboardRedisService dashboardRedisService;
    private final AiPageService aiPageService;

    public Flux<ServerSentEvent<DashboardResponseDto>> publish() {
        AiListResponseDto aiListResponseDto = aiPageService.aiListPage().toResponse();

        Dashboard dashboard = dashboardRedisService.getDashboard();

        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> DashboardResponseDto.builder()
                        .todayUser(dashboard.getTodayUser())
                        .totalUser(dashboard.getTotalUser())
                        .updatedAt(dashboard.getUpdatedAt())
                        .aiList(aiListResponseDto)
                        .build())
                .map(event -> ServerSentEvent.builder(event).build());
    }
}
