package toyspringboot.server.Domain.Entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RedisHash(value = "bashboard", timeToLive = 60L * 3)
public class Dashboard implements Serializable {
    @Id
    private String id;

    private Long totalUser;
    private Long todayUser;
    private Double todayTps;
    private Double responseTime;
    private Long useServiceCnt;
    private Timestamp updatedTime;
}
