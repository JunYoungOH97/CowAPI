package server.server.Domain.ResposneDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthResponseDto {
    private String id;
    private String email;
}
