package server.server.Domain.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserToken {
    private String userToken;
}
