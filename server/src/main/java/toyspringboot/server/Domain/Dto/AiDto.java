package toyspringboot.server.Domain.Dto;


import lombok.*;
import toyspringboot.server.Domain.ResponseDto.AiResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiDto {
    private String category;
    private String score;

    public AiResponseDto toResponse() {
        return AiResponseDto.builder()
                .category(this.category)
                .score(this.score)
                .build();
    }
}
