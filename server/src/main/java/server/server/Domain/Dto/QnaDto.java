package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Qna;
import server.server.Domain.ResposneDto.QnaResponseDto;
import server.server.Service.DateConverterComponent;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaDto {
    private final DateConverterComponent dateConverterComponent = new DateConverterComponent();


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
                .updatedAt(dateConverterComponent.DateToResponse(updatedAt))

                .email(userDto.getEmail())
                .build();
    }

    public void setCreateQna(UsersDto userDto) {
        this.setIsDeleted(false);
        this.setUpdater("API");

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.setCreatedAt(timestamp);
        this.setUpdatedAt(timestamp);

        this.setUserDto(userDto);
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
                .updater(qnADto.getUpdater())

                .createdAt(qnADto.getCreatedAt())
                .updatedAt(qnADto.getUpdatedAt())
                .deletedAt(qnADto.getDeletedAt())

                .user(UsersDto.toEntity(qnADto.getUserDto()))

                .build();
    }
}
