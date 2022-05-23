package toyspringboot.server.Domain.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAListDto {
    private List<QnADto> qnADtoList;

    public static QnAListDto of(List<QnADto> qnADtoList) {
        return QnAListDto.builder()
                .qnADtoList(qnADtoList)
                .build();
    }
}
