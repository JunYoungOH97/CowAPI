package toyspringboot.server.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import toyspringboot.server.Domain.Dto.AiDto;
import toyspringboot.server.Domain.Dto.OAuthTokenDto;

@Service
public class AiService {
    @Value(value = "${AI.postURL}")
    String postURL;

    public Mono<AiDto> responseAiResult() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.get()
                .uri(postURL)
                .retrieve()
                .bodyToMono(AiDto.class);
    }
}
