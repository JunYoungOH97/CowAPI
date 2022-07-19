package server.server.Domain.Entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RedisHash(value = "state", timeToLive = 60L * 3)
public class UserState implements Serializable {
    @Id
    private String state;
    private Boolean valid;
}
