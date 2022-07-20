package server.server.Domain.ResposneDto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDto {
    Long todayUser;
    Long totalUser;
    String updatedAt;

    AiListResponseDto aiList;
}
