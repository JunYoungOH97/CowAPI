package server.server.Domain.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AiInfo {
    @Id
    private String name;

    private Boolean isDeleted;
}
