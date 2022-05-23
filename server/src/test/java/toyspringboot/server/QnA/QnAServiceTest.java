package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Service.QnAService;
import toyspringboot.server.Service.UserService;
import toyspringboot.server.TestModule.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.User.UserTestConstants.*;

import static toyspringboot.server.QnA.QnATestConstants.*;

public class QnAServiceTest extends ServiceTest {
    @Autowired
    private QnAService qnAService;


    @Test
    @DisplayName("[Service] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        QnADto qnADto = QnADto.builder()
                .title(QnA_title)
                .content(QnA_content)
                .build();

        // when
        QnADto createdQnA = qnAService.createQnA(Exist_User_email, qnADto);

        // then
        assertEquals(createdQnA.getTitle(), QnA_title);
    }
}
