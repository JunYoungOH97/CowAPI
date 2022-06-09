package toyspringboot.server.Domain.Dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthResponseDto {
    private String id;
    private String email;
}
