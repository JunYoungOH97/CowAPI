package toyspringboot.server.Domain.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.client.HttpClientErrorException;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column
    @ColumnDefault("no content")
    private String content;

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
    @NotNull
    private String updater;

    @ManyToOne
    @JoinColumn(name = "User_email")
    private User user;


    public static QnA of(QnADto qnADto) {
        return QnA.builder()
                .id(qnADto.getId())
                .title(qnADto.getTitle())
                .content(qnADto.getContent())
                .isDeleted(qnADto.getIsDeleted())
                .createdDate(qnADto.getCreatedDate())
                .updatedDate(qnADto.getUpdatedDate())
                .deletedDate(qnADto.getDeletedDate())
                .creator(qnADto.getCreator())
                .updater(qnADto.getUpdater())
                .user(User.of(qnADto.getUserDto()))
                .build();
    }

    public void updateQnA(QnADto qnADto) {
        if(qnADto.getTitle() != null) title = qnADto.getTitle();
        if(qnADto.getContent() != null) content = qnADto.getContent();
        if(qnADto.getCreatedDate() != null) createdDate = qnADto.getCreatedDate();
        if(qnADto.getDeletedDate() != null) deletedDate = qnADto.getDeletedDate();
        if(qnADto.getCreator() != null) creator = qnADto.getCreator();
        if(qnADto.getUserDto() != null) user = User.of(qnADto.getUserDto());

        updatedDate = Timestamp.valueOf(LocalDateTime.now());
        updater = "API";
    }

    public void deleteQnA() {
        isDeleted = true;
        deletedDate = Timestamp.valueOf(LocalDateTime.now());
        updater = "API";
    }

}
