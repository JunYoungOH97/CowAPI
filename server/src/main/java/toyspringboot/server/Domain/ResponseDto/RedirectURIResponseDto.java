package toyspringboot.server.Domain.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedirectURIResponseDto {
    private String redirectURI;
}
