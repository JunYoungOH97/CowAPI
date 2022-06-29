package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.RedirectURIResponseDto;

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
