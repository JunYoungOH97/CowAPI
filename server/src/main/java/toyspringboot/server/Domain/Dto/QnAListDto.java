package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.NoticeResponseDto;
import toyspringboot.server.Domain.ResponseDto.QnAListResponseDto;
import toyspringboot.server.Domain.ResponseDto.QnAResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAListDto {
    private List<QnADto> qnADtoList;

    public QnAListResponseDto toResponse() {
        List<QnAResponseDto> qnAResponseDtos = new ArrayList<>();
        for(QnADto qnADto : qnADtoList) {
            qnAResponseDtos.add(qnADto.toResponse());
        }
        return QnAListResponseDto.builder()
                .qnADtoList(qnAResponseDtos)
                .build();
    }

    public static QnAListDto of(List<QnADto> qnADtoList) {
        return QnAListDto.builder()
                .qnADtoList(qnADtoList)
                .build();
    }
}
