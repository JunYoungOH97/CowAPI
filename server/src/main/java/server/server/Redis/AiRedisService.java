package server.server.Redis;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Entity.AiRedis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@NoArgsConstructor
public class AiRedisService {
    @Autowired
    private RedisTemplate<String, AiRedis> aiRedisTemplate;

    public void save(AiRedis ai) {
        ValueOperations<String, AiRedis> valueOperations = aiRedisTemplate.opsForValue();
        valueOperations.set(ai.getName(), ai);
    }

    public AiRedis read(String name) {
        ValueOperations<String, AiRedis> valueOperations = aiRedisTemplate.opsForValue();
        try {
            return valueOperations.get(name);

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Redis DB read error");
        }
    }

    public void update(AiDto aiDto) {
        AiRedis currentAiRedis = read(aiDto.getName());

        if(aiDto.getUseCnt() != null) currentAiRedis.setUseCnt(aiDto.getUseCnt());
        if(aiDto.getResponseTime() != null) currentAiRedis.setResponseTime(aiDto.getResponseTime());
        if(aiDto.getAccuracy() != null) currentAiRedis.setAccuracy(aiDto.getAccuracy());
        if(aiDto.getMethod() != null) currentAiRedis.setMethod(aiDto.getMethod());
        if(aiDto.getReq() != null) currentAiRedis.setReq(aiDto.getReq());
        if(aiDto.getRes() != null) currentAiRedis.setRes(aiDto.getRes());
        if(aiDto.getIsDeleted() != null) currentAiRedis.setIsDeleted(aiDto.getIsDeleted());
        if(aiDto.getUpdater() != null) currentAiRedis.setUpdater(aiDto.getUpdater());

        Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        currentAiRedis.setUpdatedAt(now);

        save(currentAiRedis);
    }
}
