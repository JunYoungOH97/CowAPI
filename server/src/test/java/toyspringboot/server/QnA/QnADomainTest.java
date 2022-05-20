package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.TestModule.DomainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.QnA.QnATestConstants.*;
import static toyspringboot.server.QnA.QnATestConstants.Create_Date;
import static toyspringboot.server.QnA.QnATestConstants.Creator_Member;
import static toyspringboot.server.User.UserTestConstants.*;

public class QnADomainTest  extends DomainTest {
    @Autowired
    private QnARepository qnARepository;

    @Test
    @DisplayName("[Domain] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .email(Exist_User_email)
                .password(Exist_User_password)
                .admin(Exist_User_admin)
                .isDeleted(Exist_User_isDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .build();


        QnA qnA = QnA.builder()
                .title(QnA_title)
                .isDeleted(QnA_isDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .user(User.of(userDto))
                .build();

        // when
        QnA newQnA = (QnA) test(qnA, qnARepository, "save");

        // then
        assertEquals(qnA.getTitle(), newQnA.getTitle());
    }
}
