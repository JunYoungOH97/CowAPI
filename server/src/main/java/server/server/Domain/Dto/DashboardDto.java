package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Dashboard;
import server.server.Domain.Repository.DashboardResponseDto;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDto {
    private String name;
    private Long todayUser;
    private Long totalUser;
    private Double responseTime;
    private Double accuracy;
    private Timestamp updatedAt;
    private Long useCnt;

    public DashboardResponseDto toResponse() {
        return DashboardResponseDto.builder()
                .totalUser(totalUser)
                .todayUser(todayUser)
                .responseTime(responseTime)
                .accuracy(accuracy)
                .updatedAt(updatedAt)
                .build();
    }

    public static DashboardDto of(Dashboard dashboard) {
        return DashboardDto.builder()
                .totalUser(dashboard.getTotalUser())
                .todayUser(dashboard.getTodayUser())
                .responseTime(dashboard.getResponseTime())
                .accuracy(dashboard.getAccuracy())
                .updatedAt(dashboard.getUpdatedAt())
                .useCnt(dashboard.getUseCnt())
                .build();
    }

    public static Dashboard toEntity(DashboardDto dashboardDto) {
        return Dashboard.builder()
                        .totalUser(dashboardDto.getTotalUser())
                        .todayUser(dashboardDto.getTodayUser())
                        .responseTime(dashboardDto.getResponseTime())
                        .accuracy(dashboardDto.getAccuracy())
                        .updatedAt(dashboardDto.getUpdatedAt())
                        .useCnt(dashboardDto.getUseCnt())
                        .build();
    }

    public static DashboardDto getInitDashboard() {
        return DashboardDto.builder()
                .name("test")
                .build();
    }
}
