package server.server.Domain.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
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
@RedisHash(value = "vgg", timeToLive = 60L * 3)
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
