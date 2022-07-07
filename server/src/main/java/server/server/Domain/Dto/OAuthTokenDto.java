package server.server.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthTokenDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresIn;
}
