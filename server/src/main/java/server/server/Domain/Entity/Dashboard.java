package server.server.Domain.Entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

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

    private String name;
    private Long todayUser;
    private Long totalUser;
    private Double responseTime;
    private Double accuracy;
    private Timestamp updatedAt;
    private Long useCnt;
}
