package server.server.Domain.ResposneDto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaListResponseDto {
    private Long lastPage;
    private List<QnaResponseDto> qnas;
}
