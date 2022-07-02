package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.ResponseDto.NoticeListResponseDto;
import toyspringboot.server.Domain.ResponseDto.NoticeResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListDto {
    private List<NoticeDto> noticeDtoList;

    public NoticeListResponseDto toResponse() {
        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
        for(NoticeDto noticeDto : noticeDtoList) {
            noticeResponseDtos.add(noticeDto.toResponse());
        }

        return NoticeListResponseDto.builder()
                .noticeDtoList(noticeResponseDtos)
                .build();
    }

    public static NoticeListDto of(List<NoticeDto> noticeDto) {
        return NoticeListDto.builder()
                .noticeDtoList(noticeDto)
                .build();
    }
}
