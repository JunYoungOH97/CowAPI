package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaResponseDto {
    Long id;
    String title;
    String content;
    Timestamp updatedAt;
    String email;
}
