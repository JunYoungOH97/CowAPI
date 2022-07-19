package server.server.Domain.Entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Users {
    @Id
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String secretKey;

    @Column
    @NotNull
    private Boolean isAdmin;

    @Column
    private Boolean isDeleted;

    @Column
    private String updater;

    @Column
    @NotNull
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private Timestamp deletedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Qna> qnas = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Notice> notices = new ArrayList<>();
}
