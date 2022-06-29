package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.TokenResponseDto;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String accessToken;

    public TokenResponseDto toResponse() {
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public static TokenDto of(String accessToken) {
        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
