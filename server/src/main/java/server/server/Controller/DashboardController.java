package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Service.DashboardService;
import server.server.Service.TestService;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    private final TestService testService;

//    @GetMapping("/dashboard")
//    public Flux<ResponseEntity<ServerSentEvent<DashboardResponseDto>>> dashboard(@RequestHeader(value = "Authorization") String token) {
//        return dashboardService.sentDashboard()
//                .map(list -> ResponseEntity.ok()
//                        .header("Authorization", token)
//                        .body(list));
//    }

    @GetMapping("/test/dashboard")
    public Flux<ServerSentEvent<DashboardResponseDto>> dashboard() {
        return dashboardService.test();
    }
}
