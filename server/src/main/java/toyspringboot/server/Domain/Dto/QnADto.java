package toyspringboot.server.Domain.Dto;

import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import lombok.*;
import org.apache.tomcat.jni.Local;
import toyspringboot.server.Domain.Entity.QnA;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnADto {
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


    public void setCreateQnA(UserDto userDto) {
        this.setIsDeleted(false);
        this.setCreator("API");
        this.setUserDto(userDto);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.setCreatedDate(timestamp);

        this.setUpdatedDate(timestamp);
        this.setUpdater("API");
    }

    public static QnADto of(QnA qnA) {
        return QnADto.builder()
                .id(qnA.getId())
                .title(qnA.getTitle())
                .content(qnA.getContent())
                .isDeleted(qnA.getIsDeleted())
                .createdDate(qnA.getCreatedDate())
                .updatedDate(qnA.getUpdatedDate())
                .deletedDate(qnA.getDeletedDate())
                .creator(qnA.getCreator())
                .updater(qnA.getUpdater())
                .userDto(UserDto.of(qnA.getUser()))
                .build();
    }
}
