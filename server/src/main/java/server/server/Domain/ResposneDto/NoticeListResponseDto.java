package server.server.Domain.ResposneDto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListResponseDto {
    private Long lastPage;
    private List<NoticeResponseDto> notices;
}
