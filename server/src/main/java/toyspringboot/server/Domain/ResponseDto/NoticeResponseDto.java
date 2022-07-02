package toyspringboot.server.Domain.ResponseDto;

import lombok.*;
import toyspringboot.server.Domain.Dto.UserDto;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponseDto {
    private String title;
    private String content;
    private Timestamp updatedDate;
    private String userEmail;
}