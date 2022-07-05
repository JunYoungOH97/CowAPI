package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Qna;
import server.server.Domain.ResposneDto.QnaResponseDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String updater;

    private UsersDto userDto;

    public QnaResponseDto toResponse() {
        return QnaResponseDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .updatedAt(updatedAt)
                .email(userDto.getEmail())
                .build();
    }

    public void setCreateQna(UsersDto userDto) {
        this.setIsDeleted(false);
        this.setUpdater("API");
        this.setUserDto(userDto);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.setCreatedAt(timestamp);

        this.setUpdatedAt(timestamp);
        this.setUpdater("API");
    }

    public static QnaDto of(Qna qnA) {
        return QnaDto.builder()
                .id(qnA.getId())
                .title(qnA.getTitle())
                .content(qnA.getContent())
                .isDeleted(qnA.getIsDeleted())
                .createdAt(qnA.getCreatedAt())
                .updatedAt(qnA.getUpdatedAt())
                .deletedAt(qnA.getDeletedAt())
                .updater(qnA.getUpdater())
                .userDto(UsersDto.of(qnA.getUser()))
                .build();
    }

    public static Qna toEntity(QnaDto qnADto) {
        return Qna.builder()
                .id(qnADto.getId())
                .title(qnADto.getTitle())
                .content(qnADto.getContent())
                .isDeleted(qnADto.getIsDeleted())
                .createdAt(qnADto.getCreatedAt())
                .updatedAt(qnADto.getUpdatedAt())
                .deletedAt(qnADto.getDeletedAt())
                .updater(qnADto.getUpdater())
                .user(UsersDto.toEntity(qnADto.getUserDto()))
                .build();
    }
}