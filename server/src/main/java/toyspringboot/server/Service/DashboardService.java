package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import toyspringboot.server.Domain.Dto.DashboardDto;
import toyspringboot.server.Domain.Entity.Dashboard;
import toyspringboot.server.Domain.Repository.UserRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.apache.tomcat.jni.Time.sleep;


@Service
@RequiredArgsConstructor
@Aspect
public class DashboardService {
    private final RedisService redisService;
    private final UserRepository userRepository;

    private static final Long refreshTime = 2L;

    public Flux<ServerSentEvent<DashboardDto>> sse() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        // 대시보드를 가져온다.
        DashboardDto dashboardDto = getDashboard();
        if(dashboardDto == null) saveDashboard(initDashboard(now));

        // 대시보드를 갱신한다.
        DashboardDto updatedDashboard = getUpdatedDashboard(now);
        updateDashboard(updatedDashboard);

        // 보낸다.
        return Flux.interval(Duration.ofSeconds(refreshTime))
                .map(sequence -> ServerSentEvent.<DashboardDto> builder()
                        .id("dashboard")
                        .event("periodic-event")
                        .data(getDashboard())
                        .retry(Duration.ofSeconds(refreshTime))
                        .build());
    }

    public boolean saveDashboard(DashboardDto dashboardDto) {
        return redisService.saveDashboard(DashboardDto.toEntity(dashboardDto));
    }

    public DashboardDto getDashboard() {
        try {
            return DashboardDto.of(redisService.getDashboard());
        } catch (NullPointerException e){
            return null;
        }
    }

    public boolean updateDashboard(DashboardDto dashboardDto) {
        return redisService.updateDashboard(DashboardDto.toEntity(dashboardDto));
    }

    public Long getTodayUser() {
        return userRepository.getTodaySignup();

    }

    public Long getTotalUser() {
        return userRepository.count();
    }

    public DashboardDto initDashboard(Timestamp now) {
        return DashboardDto.builder()
                .todayUser(0L)
                .totalUser(0L)
                .useServiceCnt(0L)
                .responseTime(0.0D)
                .responseTime(0.0D)
                .updatedTime(now)
                .build();
    }

    public DashboardDto getUpdatedDashboard(Timestamp now) {
        return DashboardDto.builder()
                .todayUser(getTodayUser())
                .totalUser(getTotalUser())
                .updatedTime(now)
                .build();
    }

    @Around(value = "execution(* *..Service.AiService..*(..))")
    public Object test(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        Double timeMs = (System.currentTimeMillis() - start) * 1.0D;

        DashboardDto dashboardDto = getDashboard();

        Long updateUserCnt = dashboardDto.getUseServiceCnt() + 1L;
        Double updateResponseTime = (dashboardDto.getResponseTime() + timeMs) / updateUserCnt;
        Double updateTPS = (updateResponseTime.equals(0.0D)) ? 0.0D : 1000.0D / updateResponseTime;

        dashboardDto.setUseServiceCnt(updateUserCnt);
        dashboardDto.setResponseTime(updateResponseTime);
        dashboardDto.setTodayTps(updateTPS);

        updateDashboard(dashboardDto);

        return result;
    }
}
