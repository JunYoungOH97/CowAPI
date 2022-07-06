package server.server.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiRequestDto {
    private String s3Path;
}
