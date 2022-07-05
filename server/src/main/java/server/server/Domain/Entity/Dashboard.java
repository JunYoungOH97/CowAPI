package server.server.Domain.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Dashboard {
    @Id
    private String name;

    @Column
    private Long totalUser;

    @Column
    private Long todayUser;

    @Column
    @NotNull
    private String updater;

    @Column
    @NotNull
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private Timestamp deletedAt;
}
