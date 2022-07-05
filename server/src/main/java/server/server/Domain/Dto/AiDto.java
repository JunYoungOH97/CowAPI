package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Ai;
import server.server.Domain.ResposneDto.AiResponseDto;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiDto {
    private String name;
    private String field;
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


    public static AiDto of(Ai ai) {
        return AiDto.builder()
                .name(ai.getName())
                .field(ai.getField())
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
                .deletedAt(ai.getDeletedAt())
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
                .updatedAt(updatedAt)
                .build();
    }
}
