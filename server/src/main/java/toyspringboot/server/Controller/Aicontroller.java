package toyspringboot.server.Controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.AiDto;
import toyspringboot.server.Service.AiService;

@Api(tags = {"AI api"})
@RestController
@RequiredArgsConstructor
public class Aicontroller {
    private final AiService aiService;

    @GetMapping("/ai/result")
    public Mono<AiDto> aiResponse() {
        return aiService.responseAiResult();
    }
}
