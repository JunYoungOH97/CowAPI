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
@RedisHash(value = "vgg", timeToLive = -1L)
public class AiRedis implements Serializable {
    @Id
    private String name;

    private String field;
    private Double responseTime;
    private Double accuracy;
    private String requestURI;
    private String method;
    private String req;
    private String res;
    private Boolean isDeleted;
    private String updater;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
}
