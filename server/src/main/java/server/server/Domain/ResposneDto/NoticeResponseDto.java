package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeResponseDto {
    Long id;
    String title;
    String content;
    String updatedAt;
    String email;
}
