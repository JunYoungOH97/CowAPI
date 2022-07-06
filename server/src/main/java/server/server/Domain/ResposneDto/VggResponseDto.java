package server.server.Domain.ResposneDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VggResponseDto {
    private String category;
    private Double score;
}
