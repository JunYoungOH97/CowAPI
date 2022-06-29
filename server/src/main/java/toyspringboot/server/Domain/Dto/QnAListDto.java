package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.QnAListResponseDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAListDto {
    private List<QnADto> qnADtoList;

    public QnAListResponseDto toResponse() {
        return QnAListResponseDto.builder()
                .qnADtoList(qnADtoList)
                .build();
    }

    public static QnAListDto of(List<QnADto> qnADtoList) {
        return QnAListDto.builder()
                .qnADtoList(qnADtoList)
                .build();
    }
}
