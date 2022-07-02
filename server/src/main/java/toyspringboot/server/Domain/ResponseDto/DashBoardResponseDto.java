package toyspringboot.server.Domain.ResponseDto;

import lombok.*;

import java.sql.Timestamp;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashBoardResponseDto {
    private Long totalUser;
    private Long todayUser;
    private Double todayTps;
    private Double responseTime;
    private Timestamp updatedTime;
}
