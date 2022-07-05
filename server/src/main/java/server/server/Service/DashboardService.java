package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import server.server.Domain.Repository.DashboardRepository;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private static final Long refreshTime = 2L;
    private final DashboardRepository dashboardRepository;

    public Flux<ServerSentEvent<DashboardResponseDto>> sentDashboard() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        AiResponseDto aiResponseDto = AiResponseDto.builder()
                .name("vgg")
                .responseTime(0.1)
                .accuracy(1.0)
                .build();

        AiResponseDto aiResponseDto2 = AiResponseDto.builder()
                .name("language")
                .responseTime(0.5)
                .accuracy(1.0)
                .build();

        List<AiResponseDto> aiResponseDtoList = new ArrayList<>();
        aiResponseDtoList.add(aiResponseDto);
        aiResponseDtoList.add(aiResponseDto2);

        AiListResponseDto ais = AiListResponseDto.builder()
                .aiList(aiResponseDtoList)
                .build();


        DashboardResponseDto dashboardResponseDto = DashboardResponseDto.builder()
                .todayUser(1L)
                .totalUser(1L)
                .updatedAt(now)
                .aiList(ais)
                .build();

        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardResponseDto> builder()
                        .id("/dashboard")
                        .event("periodic-event")
                        .data(dashboardResponseDto)
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

}
