package server.server.Domain.Dto;

import lombok.*;
import server.server.Domain.Entity.Notice;
import server.server.Domain.Entity.Qna;
import server.server.Domain.ResposneDto.NoticeResponseDto;
import server.server.Domain.ResposneDto.QnaResponseDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String updater;

    private UsersDto userDto;

    public void setCreateNotice(UsersDto userDto) {
        this.setIsDeleted(false);
        this.setUpdater("API");
        this.setUserDto(userDto);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.setCreatedAt(timestamp);

        this.setUpdatedAt(timestamp);
        this.setUpdater("API");
    }

    public static Notice toEntity(NoticeDto noticeDto) {
        return Notice.builder()
                .id(noticeDto.getId())
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .isDeleted(noticeDto.getIsDeleted())
                .createdAt(noticeDto.getCreatedAt())
                .updatedAt(noticeDto.getUpdatedAt())
                .deletedAt(noticeDto.getDeletedAt())
                .updater(noticeDto.getUpdater())
                .user(UsersDto.toEntity(noticeDto.getUserDto()))
                .build();
    }

    public static NoticeDto of(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .isDeleted(notice.getIsDeleted())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .deletedAt(notice.getDeletedAt())
                .updater(notice.getUpdater())
                .userDto(UsersDto.of(notice.getUser()))
                .build();
    }

    public NoticeResponseDto toResponse() {
        return NoticeResponseDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .updatedAt(updatedAt)
                .email(userDto.getEmail())
                .build();
    }
}
