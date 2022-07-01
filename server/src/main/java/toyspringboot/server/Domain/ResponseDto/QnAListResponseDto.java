package toyspringboot.server.Domain.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import toyspringboot.server.Domain.Dto.QnADto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAListResponseDto {
    private List<QnADto> qnADtoList;
}
