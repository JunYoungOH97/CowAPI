package toyspringboot.server.Domain.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.server.ResponseStatusException;
import toyspringboot.server.Domain.Dto.UserDto;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class User {
    @Id
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private Boolean admin;

    @Column
    @NotNull
    private Boolean isDeleted;

    @Column
    @NotNull
    private Timestamp createdDate;

    @Column
    @NotNull
    private Timestamp updatedDate;

    @Column
    private Timestamp deletedDate;

    @Column
    @NotNull
    private String creator;

    @Column
    private String updater;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<QnA> QnAs = new ArrayList<>();
}
