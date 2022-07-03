package server.server.Domain.Repository;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDto {
    String name;
    Long todayUser;
    Long totalUser;
    Double responseTime;
    Double accuracy;
    Timestamp updatedAt;
}
