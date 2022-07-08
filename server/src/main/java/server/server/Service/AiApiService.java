package server.server.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Dto.AiRequestDto;
import server.server.Domain.Dto.UsersDto;
import server.server.Domain.Entity.AiRedis;
import server.server.Domain.Entity.Dashboard;
import server.server.Domain.Entity.Users;
import server.server.Domain.Repository.UsersRepository;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.DashboardResponseDto;
import server.server.Service.AiApiFactory.VggResponseDto;
import server.server.Redis.AiRedisService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AiApiService {
    private final UsersRepository usersRepository;
    private final AiRedisService aiRedisService;

    @Value(value = "${AI.postURL}")
    String postURL;

    public void isValidUser(UsersDto usersDto) {
        Users user = usersRepository.findByEmail(usersDto.getEmail()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다."));
        if(!user.getSecretKey().equals(usersDto.getSecretKey())) throw new ResponseStatusException(NOT_FOUND, "존재하지 않는 사용자 입니다.");
    }

    public VggResponseDto vggResponse(String name, String s3Path) {
        AiRequestDto aiRequestDto = AiRequestDto.builder().s3Path(s3Path).build();

        WebClient webClient = WebClient.create("http://localhost:8080");

        long start = System.currentTimeMillis();
        VggResponseDto vggResponseDto = webClient.post()
                .uri(postURL)
                .bodyValue(aiRequestDto)
                .retrieve()
                .bodyToMono(VggResponseDto.class)
                .block();

        if(vggResponseDto == null) throw new ResponseStatusException(NOT_ACCEPTABLE, "모델에 문제가 생겼습니다.");
        Double timeMs = (System.currentTimeMillis() - start) * 1.0D;

        AiRedis aiRedis = aiRedisService.read(name);

        Long oldUseCnt = aiRedis.getUseCnt();
        Double oldAccuracy = aiRedis.getAccuracy();

        long newUseCnt = aiRedis.getUseCnt() + 1L;
        Double newAccuracy = ((oldAccuracy * oldUseCnt) + vggResponseDto.getScore()) / newUseCnt;
        Double newResponseTime = (aiRedis.getResponseTime() + timeMs) / newUseCnt;

        AiDto aiDto = AiDto.builder()
                .name(name)
                .accuracy(newAccuracy)
                .useCnt(newUseCnt)
                .responseTime(newResponseTime)
                .build();

        aiRedisService.update(aiDto);

        return vggResponseDto;
    }
}
