package server.server.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Service.AiPageService;

@RestController
@RequiredArgsConstructor
@Api(tags = {"AI"})
public class AiPageController {
    private final AiPageService aiPageService;

    @ApiOperation(value = "AI page", notes = "각각의 AI를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("/ai/{name}")
    public ResponseEntity<AiResponseDto> aiOnePage(@RequestHeader("Authorization") String userToken,
                                                   @PathVariable(value = "name") String name) {
        return ResponseEntity.ok()
                .body(aiPageService.aiOnePage(AiDto.builder().name(name).build()).toResponse());
    }

    @ApiOperation(value = "AI List", notes = "AI list를 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @GetMapping("ais")
    public ResponseEntity<AiListResponseDto> aiListPage(@RequestHeader("Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(aiPageService.aiListPage().toResponse());
    }
}
