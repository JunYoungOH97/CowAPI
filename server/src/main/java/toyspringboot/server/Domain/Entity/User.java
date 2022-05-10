package toyspringboot.server.Domain.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import toyspringboot.server.Domain.Dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private boolean admin;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Notice> notices = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Answer> answers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<QnA> QnAs = new ArrayList<>();

    public static User of(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .admin(userDto.isAdmin())
                .notices(userDto.getNotices())
                .answers(userDto.getAnswers())
                .QnAs(userDto.getQnAs())
                .build();
    }
}
