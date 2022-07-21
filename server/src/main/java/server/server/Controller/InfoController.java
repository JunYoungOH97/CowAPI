package server.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.server.Service.InfoService;

@RestController
@RequiredArgsConstructor
@Api(tags = {"init Ingo"})
public class InfoController {
    private final InfoService infoService;

    @ApiOperation(value = "대시보드", notes = "관리자만이 사용하는 API 로 대시보드를 초기화 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/dev/init/dashboard")
    public void initD() {
        infoService.saveDashboard();
    }

    @ApiOperation(value = "Vgg", notes = "관리자만이 사용하는 API 로 AI모델을 초기화 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/dev/init/vgg")
    public void initV() {
        infoService.saveVgg();
    }

    @ApiOperation(value = "Lang", notes = "관리자만이 사용하는 API 로 AI모델을 초기화 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @PostMapping("/dev/init/lang")
    public void initL() {
        infoService.saveLang();
    }
}
