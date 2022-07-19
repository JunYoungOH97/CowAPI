package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.ResposneDto.AiListResponseDto;
import server.server.Domain.ResposneDto.AiResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiListDto {
    List<AiDto> aiDtoList;

    public AiListResponseDto toResponse() {
        List<AiResponseDto> aiResponseDtoList = new ArrayList<>();
        for(AiDto aiDto : aiDtoList) {
            aiResponseDtoList.add(aiDto.toResponse());
        }
        return AiListResponseDto.builder()
                .aiList(aiResponseDtoList)
                .build();
    }
}
