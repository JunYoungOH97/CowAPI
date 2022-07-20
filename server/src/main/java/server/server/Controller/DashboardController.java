package server.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Service.DashboardService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Api(tags = {"Dashboard"})
public class DashboardController {
    private final DashboardService dashboardService;

    @ApiOperation(value = "대시보드", notes = "대시보드를 SSE를 통해 실시간으로 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/dashboard")
    public Flux<ServerSentEvent<DashboardResponseDto>> dashboard(HttpServletRequest request) {
        return dashboardService.publish(request);
    }
}
