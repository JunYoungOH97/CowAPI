package toyspringboot.server.Controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Service.DashboardService;

@Api(tags = {"대시보드"})
@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping(value = "/dashboard")
    public ResponseEntity<Flux<ServerSentEvent<DashboardDto>>> sseDashboard() {
        return new ResponseEntity<>(dashboardService.sse(), HttpStatus.OK);
    }
}
