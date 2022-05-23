package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.QnADto;
import toyspringboot.server.Domain.Dto.UserDto;
import toyspringboot.server.Domain.Entity.QnA;
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

    @Autowired
    private QnARepository qnARepository;

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

    @Test
    @DisplayName("[Service] QnA 조회 테스트")
    public void readTest() {
        // given
        QnADto qnADto = QnADto.of(qnARepository.findById(Exist_QnA_id).get());

        // when
        QnADto foundQnA = qnAService.readQnA(qnADto);

        // then
        assertEquals(foundQnA.getTitle(), Exist_QnA_title);
    }

    @Test
    @DisplayName("[Service] QnA 수정 테스트")
    public void updateTest() {
        // given
        QnADto qnADto = QnADto.of(qnARepository.findById(Exist_QnA_id).get());

        qnADto.setContent(QnA_content);

        // when
        QnADto updatedQnA = qnAService.updateQnA(Exist_User_email, qnADto);

        // then
        QnA qnA = qnARepository.findById(updatedQnA.getId()).get();

        assertEquals(qnA.getId(), Exist_QnA_id);
        assertEquals(qnA.getContent(), QnA_content);

    }

}
