package server.server.Domain.ResposneDto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserResponseDto {
    private String email;
    private String authorization;
    private Boolean isAdmin;
}
