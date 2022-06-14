package toyspringboot.server.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.AiDto;
import toyspringboot.server.Domain.Dto.AiRequestDto;
import toyspringboot.server.Domain.Dto.OAuthTokenDto;

@Service
public class AiService {
    @Value(value = "${AI.postURL}")
    String postURL;

    public AiDto responseAiResult(String s3Path) {
        AiRequestDto aiRequestDto = AiRequestDto.builder().s3Path(s3Path).build();

        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.post()
                .uri(postURL)
                .bodyValue(aiRequestDto)
                .retrieve()
                .bodyToMono(AiDto.class)
                .block();
    }
}
