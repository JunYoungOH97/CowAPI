package server.server.Dto.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserInfoResponseDto {
    private String id;
    private String email;
}
