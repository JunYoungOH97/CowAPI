package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import server.server.Domain.Dto.DashboardDto;
import server.server.Domain.Repository.DashboardResponseDto;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final RedisService redisService;

    private static final Long refreshTime = 2L;

    public Flux<ServerSentEvent<DashboardResponseDto>> serverSentEvent() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        // 대시보드를 가져온다.
        DashboardDto dashboardDto = DashboardDto.of(redisService.getDashboard());
        dashboardDto.setUpdatedAt(now);

        // 대시보드를 갱신한다.
        redisService.updateDashboard(DashboardDto.toEntity(dashboardDto));

        // 보낸다.
        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardResponseDto> builder()
                        .id("/dashboard")
                        .event("periodic-event")
                        .data(DashboardDto.of(redisService.getDashboard()).toResponse())
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

}
