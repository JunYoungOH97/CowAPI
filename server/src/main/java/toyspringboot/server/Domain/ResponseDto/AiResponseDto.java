package toyspringboot.server.Domain.ResponseDto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResponseDto {
    private String category;
    private String score;
}
