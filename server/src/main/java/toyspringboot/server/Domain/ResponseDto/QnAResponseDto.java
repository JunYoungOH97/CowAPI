package toyspringboot.server.Domain.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import toyspringboot.server.Domain.Dto.UserDto;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnAResponseDto {
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String email;
}
