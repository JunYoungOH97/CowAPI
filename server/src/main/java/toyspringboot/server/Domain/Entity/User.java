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

    public static User of(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .admin(userDto.getAdmin())
                .isDeleted(userDto.getIsDeleted())
                .createdDate(userDto.getCreatedDate())
                .updatedDate(userDto.getUpdatedDate())
                .deletedDate(userDto.getDeletedDate())
                .creator(userDto.getCreator())
                .updater(userDto.getUpdater())
                .build();
    }

    public void updateUser(UserDto userDto) {
        if(userDto.getPassword() != null) password = userDto.getPassword();
        if(userDto.getAdmin() != null) admin = userDto.getAdmin();
        if(userDto.getIsDeleted() != null) isDeleted = userDto.getIsDeleted();
        if(userDto.getCreatedDate() != null) createdDate = userDto.getCreatedDate();
        if(userDto.getUpdatedDate() != null) updatedDate = Timestamp.valueOf(LocalDateTime.now());
        if(userDto.getDeletedDate() != null) deletedDate = userDto.getDeletedDate();
        if(userDto.getCreator() != null) creator = userDto.getCreator();
        if(userDto.getUpdater() != null) updater = "API";
    }

    public void deleteUser() {
        if(isDeleted.equals(false)) {
            isDeleted = true;
            deletedDate = Timestamp.valueOf(LocalDateTime.now());
            updater = "API";
        }
        else {
            throw new ResponseStatusException(BAD_REQUEST, "이미 삭제된 유저 입니다.");
        }
    }
}
