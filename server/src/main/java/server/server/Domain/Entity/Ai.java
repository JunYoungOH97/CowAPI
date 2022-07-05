package server.server.Domain.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Ai {
    @Id
    private String name;

    @Column
    private String field;

    @Column
    private Double responseTime;

    @Column
    private Double accuracy;

    @Column
    private String requestURI;

    @Column
    private String method;

    @Column
    private String req;

    @Column
    private String res;

    @Column
    @NotNull
    private Boolean isDeleted;

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
