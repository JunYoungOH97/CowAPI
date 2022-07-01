package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.OAuthUserInfoResponseDto;

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
