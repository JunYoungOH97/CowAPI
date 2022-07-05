package server.server.Controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Entity.Ai;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Service.AiPageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AiPageController {
    private final AiPageService aiPageService;

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

}
