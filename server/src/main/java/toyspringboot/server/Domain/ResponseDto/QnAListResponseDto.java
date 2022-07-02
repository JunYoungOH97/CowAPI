package toyspringboot.server.Domain.ResponseDto;

import lombok.*;
import toyspringboot.server.Domain.Dto.QnADto;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAListResponseDto {
    private List<QnAResponseDto> qnADtoList;
}
