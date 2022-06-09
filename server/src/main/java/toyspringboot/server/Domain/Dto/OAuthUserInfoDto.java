package toyspringboot.server.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserInfoDto {
    private String resultcode;
    private String message;
    private OAuthResponseDto response;
}
