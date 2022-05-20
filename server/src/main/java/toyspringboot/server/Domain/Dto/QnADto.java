package toyspringboot.server.Domain.Dto;

import lombok.*;

import java.sql.Timestamp;

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
}
