package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import server.server.Domain.Repository.DashboardResponseDto;
import server.server.Service.DashboardService;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping(value = "/dashboard")
    public ResponseEntity<Flux<ServerSentEvent<DashboardResponseDto>>> sseDashboard() {
        return ResponseEntity.ok()
                .body(dashboardService.serverSentEvent());
    }
}
