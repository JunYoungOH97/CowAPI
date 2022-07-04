package server.server.Dto.ResposneDto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiListResponseDto {
    List<AiResponseDto> aiResponseDtoList;
}
