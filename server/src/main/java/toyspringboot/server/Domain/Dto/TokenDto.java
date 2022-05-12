package toyspringboot.server.Domain.Dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String accessToken;

    public static TokenDto of(String accessToken) {
        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
