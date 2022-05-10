package toyspringboot.server.Domain.Dto;


import lombok.*;
import toyspringboot.server.Domain.Entity.Answer;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private boolean admin;

    private List<Notice> notices;
    private List<Answer> answers;
    private List<QnA> QnAs;

    public static UserDto of(User userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .admin(userEntity.isAdmin())
                .notices(userEntity.getNotices())
                .answers(userEntity.getAnswers())
                .QnAs(userEntity.getQnAs())
                .build();

    }


}
