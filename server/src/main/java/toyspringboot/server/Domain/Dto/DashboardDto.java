package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.Entity.Dashboard;
import toyspringboot.server.Domain.ResponseDto.DashBoardResponseDto;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDto {
    private String id;
    private Long totalUser;
    private Long todayUser;
    private Double todayTps;
    private Double responseTime;
    private Long useServiceCnt;
    private Timestamp updatedTime;

    public DashBoardResponseDto toResponse() {
        return DashBoardResponseDto.builder()
                .totalUser(totalUser)
                .todayUser(todayUser)
                .todayTps(todayTps)
                .responseTime(responseTime)
                .updatedTime(updatedTime)
                .build();
    }

    public static DashboardDto of(Dashboard dashboard) {
        return DashboardDto.builder()
                .id(dashboard.getId())
                .totalUser(dashboard.getTotalUser())
                .todayUser(dashboard.getTodayUser())
                .todayTps(dashboard.getTodayTps())
                .useServiceCnt(dashboard.getUseServiceCnt())
                .responseTime(dashboard.getResponseTime())
                .updatedTime(dashboard.getUpdatedTime())
                .build();
    }

    public static Dashboard toEntity(DashboardDto dashboardDto) {
        return Dashboard.builder()
                .id(dashboardDto.getId())
                .totalUser(dashboardDto.getTotalUser())
                .todayUser(dashboardDto.getTodayUser())
                .todayTps(dashboardDto.getTodayTps())
                .responseTime(dashboardDto.getResponseTime())
                .useServiceCnt(dashboardDto.getUseServiceCnt())
                .updatedTime(dashboardDto.getUpdatedTime())
                .build();
    }
}
