package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Dto.AiListDto;
import server.server.Domain.Entity.*;
import server.server.Domain.Repository.AiInfoRepository;
import server.server.Redis.AiRedisService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiPageService {
    private final AiRedisService aiRedisService;
    private final AiInfoRepository aiInfoRepository;

    public AiDto aiOnePage(AiDto aiDto) {
        AiRedis aiRedis = aiRedisService.read(aiDto.getName());

        if(aiRedis == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 AI 서비스 입니다.");

        return AiDto.of(aiRedis);
    }

    public AiListDto aiListPage() {
        List<AiInfo> aiInfoList = aiInfoRepository.findAiByExist().orElseThrow();

        List<AiDto> aiDtoList = new ArrayList<>();
        for(AiInfo aiInfo : aiInfoList) {
            AiRedis aiRedis = aiRedisService.read(aiInfo.getName());
            aiDtoList.add(AiDto.of(aiRedis));
        }

        return AiListDto.builder().aiDtoList(aiDtoList).build();
    }
}
