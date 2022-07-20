package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.AiRedis;
import server.server.Domain.ResposneDto.AiResponseDto;
import server.server.Service.DateConverterComponent;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiDto {
    private final DateConverterComponent dateConverterComponent = new DateConverterComponent();

    private String name;
    private String field;
    private Long useCnt;
    private Double responseTime;
    private Double accuracy;

    private String requestURI;
    private String method;
    private String req;
    private String res;
    private Boolean isDeleted;
    private String updater;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static AiDto of(AiRedis ai) {
        return AiDto.builder()
                .name(ai.getName())
                .field(ai.getField())
                .useCnt(ai.getUseCnt())
                .responseTime(ai.getResponseTime())
                .accuracy(ai.getAccuracy())

                .requestURI(ai.getRequestURI())
                .method(ai.getMethod())
                .req(ai.getReq())
                .res(ai.getRes())
                .isDeleted(ai.getIsDeleted())
                .updater(ai.getUpdater())
                .createdAt(ai.getCreatedAt())
                .updatedAt(ai.getUpdatedAt())
                .build();
    }

    public AiResponseDto toResponse() {
        return AiResponseDto.builder()
                .name(name)
                .field(field)
                .responseTime(responseTime)
                .accuracy(accuracy)

                .requestURI(requestURI)
                .method(method)
                .req(req)
                .res(res)
                .updatedAt(dateConverterComponent.DateToResponse(updatedAt))
                .build();

    }
}
