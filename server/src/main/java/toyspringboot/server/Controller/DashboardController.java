package toyspringboot.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import toyspringboot.server.Domain.ResponseDto.DashBoardResponseDto;
import toyspringboot.server.Service.DashboardService;

@Api(tags = {"Dash board"})
@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @ApiOperation(value = "Dash Board", notes = "홈 화면인 대시보드 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping(value = "/dashboard")
    public ResponseEntity<Flux<ServerSentEvent<DashBoardResponseDto>>> sseDashboard() {
        return ResponseEntity.ok()
                .body(dashboardService.sse());
    }
}
