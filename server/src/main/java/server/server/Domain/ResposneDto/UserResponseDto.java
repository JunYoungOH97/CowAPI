package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    String email;
    String createdAt;
    String secretKey;
}
