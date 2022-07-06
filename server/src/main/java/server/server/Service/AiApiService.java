package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import server.server.Domain.Dto.AiRequestDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;
import server.server.Domain.ResposneDto.VggResponseDto;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AiApiService {
    private final UsersRepository usersRepository;

    @Value(value = "${AI.postURL}")
    String postURL;

    public void isValidUser(UsersDto usersDto) {
        Users user = usersRepository.findByEmail(usersDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        if(!user.getSecretKey().equals(usersDto.getSecretKey())) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.");
    }


    public Mono<VggResponseDto> vggResponse(String s3Path) {
        AiRequestDto aiRequestDto = AiRequestDto.builder().s3Path(s3Path).build();

        WebClient webClient = WebClient.create("http://localhost:8080");

        return webClient.post()
                .uri(postURL)
                .bodyValue(aiRequestDto)
                .retrieve()
                .bodyToMono(VggResponseDto.class);
    }
}
