package toyspringboot.server.Domain.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListDto {
    private List<NoticeDto> noticeDtoList;

    public static NoticeListDto of(List<NoticeDto> noticeDto) {
        return NoticeListDto.builder()
                .noticeDtoList(noticeDto)
                .build();
    }
}
