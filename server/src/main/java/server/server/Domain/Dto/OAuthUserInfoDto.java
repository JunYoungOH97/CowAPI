package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.ResposneDto.OAuthResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserInfoDto {
    private String resultCode;
    private String message;
    private OAuthResponseDto response;

    public OAuthUserInfoResponseDto toResponse() {
        return OAuthUserInfoResponseDto.builder()
                .email(response.getEmail())
                .build();
    }
}
