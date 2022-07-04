package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    public Flux<ServerSentEvent<DashboardResponseDto>> sentDashboard() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        long refreshTime = 2L;

        List<AiResponseDto> aiResponseDtoList = new ArrayList<>();

        AiResponseDto aiResponseDto = AiResponseDto.builder()
                .name("test")
                .totalUser(1L)
                .todayUser(1L)
                .responseTime(0.1)
                .accuracy(1.0)
                .updatedAt(now)
                .build();
        aiResponseDtoList.add(aiResponseDto);

        AiListResponseDto aiResponseListDto = AiListResponseDto.builder()
                .aiResponseDtoList(aiResponseDtoList)
                .build();

        DashboardResponseDto aiList = DashboardResponseDto.builder()
                .aiList(aiResponseListDto)
                .build();

        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardResponseDto> builder()
                        .id("/dashboard")
                        .event("periodic-event")
                        .data(aiList)
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

}
