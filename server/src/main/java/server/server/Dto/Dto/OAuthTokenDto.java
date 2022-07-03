package server.server.Dto.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthTokenDto {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
