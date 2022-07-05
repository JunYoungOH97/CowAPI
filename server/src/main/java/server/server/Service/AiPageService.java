package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Dto.AiListDto;
import server.server.Domain.Dto.QnaDto;
import server.server.Domain.Entity.*;
import server.server.Domain.Repository.AiInfoRepository;
import server.server.Domain.Repository.AiPageRepository;
import server.server.Redis.AiRedisService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiPageService {
    private final AiPageRepository aiPageRepository;
    private final AiRedisService aiRedisService;
    private final AiInfoRepository aiInfoRepository;

    public AiDto aiOnePage(AiDto aiDto) {
        AiRedis aiRedis = aiRedisService.read(aiDto.getName());
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
