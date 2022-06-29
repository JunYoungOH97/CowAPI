package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.NoticeListResponseDto;
import toyspringboot.server.Domain.ResponseDto.NoticeResponseDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListDto {
    private List<NoticeDto> noticeDtoList;

    public NoticeListResponseDto toResponse() {
        return NoticeListResponseDto.builder()
                .noticeDtoList(noticeDtoList)
                .build();
    }

    public static NoticeListDto of(List<NoticeDto> noticeDto) {
        return NoticeListDto.builder()
                .noticeDtoList(noticeDto)
                .build();
    }
}
