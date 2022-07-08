package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Service.AiPageService;
import server.server.Service.DashboardService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

//    @GetMapping("/dashboard/{id}")
//    public SseEmitter dashboard(@PathVariable(value = "id") Long id) throws IOException {
//        return dashboardService.subscribe(id);
//    }

//    @GetMapping("/dashboard/{id}")
//    public Flux<ServerSentEvent<DashboardResponseDto>> dashboard(@PathVariable(value = "id") Long id) throws IOException {
//        return dashboardService.publish(id);
//    }

    @GetMapping("/dashboard")
    public Flux<ServerSentEvent<DashboardResponseDto>> dashboard(HttpServletRequest request) {
        return dashboardService.publish(request);
    }
}
