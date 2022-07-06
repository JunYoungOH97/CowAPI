package server.server.Service.AiApiFactory;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VggResponseDto implements AiResponse {
    private String category;
    private Double score;

    @Override
    public VggResponseDto toResponse() {
        return this;
    }
}
