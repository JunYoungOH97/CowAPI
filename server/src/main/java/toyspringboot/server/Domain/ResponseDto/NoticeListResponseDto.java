package toyspringboot.server.Domain.ResponseDto;

import lombok.*;
import toyspringboot.server.Domain.Dto.NoticeDto;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListResponseDto {
    private List<NoticeResponseDto> noticeDtoList;
}
