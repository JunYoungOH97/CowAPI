package toyspringboot.server.Domain.Dto;

import lombok.*;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserInfoDto {
    private String email;
}
