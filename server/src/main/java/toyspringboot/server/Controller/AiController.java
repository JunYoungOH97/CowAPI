package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.AiDto;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Service.AiService;
import toyspringboot.server.Service.RedisService;

@Api(tags = {"AI api"})
@RestController
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;
    private final RedisService redisService;

    @GetMapping("/ai/result")
    public Mono<AiDto> aiResponse() {
        Mono<AiDto> result = aiService.responseAiResult();
        return result;
    }
}
