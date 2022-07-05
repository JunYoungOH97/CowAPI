package server.server.Domain.ResposneDto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {
    private String authorization;
    private Boolean isAdmin;
}
