package server.server.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Service.AiPageService;
import server.server.Service.TestService;

@RestController
@RequiredArgsConstructor
public class AiPageController {
    private final AiPageService aiPageService;

    private final TestService testService;

    @GetMapping("/ai/{name}")
    public ResponseEntity<AiResponseDto> aiOnePage(@RequestHeader("Authorization") String userToken,
                                                   @PathVariable(value = "name") String name) {
        return ResponseEntity.ok()
                .body(aiPageService.aiOnePage(AiDto.builder().name(name).build()).toResponse());
    }

    @GetMapping("ais")
    public ResponseEntity<AiListResponseDto> aiListPage(@RequestHeader("Authorization") String userToken) {
        return ResponseEntity.ok()
                .body(aiPageService.aiListPage().toResponse());
    }

    @PostMapping("/test/ai")
    public void aiTest() {
        testService.saveVgg();
        testService.saveLang();
    }

}
