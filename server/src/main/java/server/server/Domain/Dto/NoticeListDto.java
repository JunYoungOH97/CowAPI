package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.ResposneDto.NoticeListResponseDto;
import server.server.Domain.ResposneDto.NoticeResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListDto {
    private Long lastPage;
    private List<NoticeDto> noticeDtoList;

    public NoticeListResponseDto toResponse() {
        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
        for(NoticeDto noticeDto : noticeDtoList) {
            noticeResponseDtos.add(noticeDto.toResponse());
        }

        return NoticeListResponseDto.builder()
                .lastPage(lastPage)
                .notices(noticeResponseDtos)
                .build();
    }
}
