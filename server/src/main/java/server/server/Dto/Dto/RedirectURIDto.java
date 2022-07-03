package server.server.Dto.Dto;

import lombok.*;
import server.server.Dto.ResposneDto.RedirectURIResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedirectURIDto {
    private String redirectURI;

    public RedirectURIResponseDto toResponse() {
        return RedirectURIResponseDto.builder()
                .redirectURI(redirectURI)
                .build();
    }
}
