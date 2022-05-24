package toyspringboot.server.Domain.Dto;

import lombok.*;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.User;

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
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Timestamp deletedDate;
    private String creator;
    private String updater;

    private UserDto userDto;

    public void setCreateNoticeDto(UserDto userDto) {
        this.setIsDeleted(false);
        this.setUserDto(userDto);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.setCreatedDate(timestamp);
        this.setCreator("API");

        this.setUpdatedDate(timestamp);
        this.setUpdater("API");
    }
}
