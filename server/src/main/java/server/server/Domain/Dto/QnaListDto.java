package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.ResposneDto.QnaListResponseDto;
import server.server.Domain.ResposneDto.QnaResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaListDto {
    private Long lastPage;
    private List<QnaDto> qnADtoList;

    public QnaListResponseDto toResponse() {
        List<QnaResponseDto> qnAResponseDtos = new ArrayList<>();
        for(QnaDto qnADto : qnADtoList) {
            qnAResponseDtos.add(qnADto.toResponse());
        }

        return QnaListResponseDto.builder()
                .lastPage(lastPage)
                .qnas(qnAResponseDtos)
                .build();
    }
}
