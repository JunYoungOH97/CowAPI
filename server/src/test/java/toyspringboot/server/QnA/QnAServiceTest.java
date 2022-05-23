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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(QnA_title, createdQnA.getTitle());
    }

    @Test
    @DisplayName("[Service] QnA 조회 테스트")
    public void readTest() {
        // given
        // when
        QnADto foundQnA = qnAService.readQnA(Exist_User_email, Exist_QnA_id);

        // then
        assertEquals(Exist_QnA_title, foundQnA.getTitle());
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

        assertEquals(Exist_QnA_id, qnA.getId());
        assertEquals(QnA_content, qnA.getContent());
    }

    @Test
    @DisplayName("[Service] QnA 삭제 테스트")
    public void deleteTest() {
        // given
        QnADto qnADto = QnADto.of(qnARepository.findById(Exist_QnA_id).get());

        // when
        QnADto deletedQnA = qnAService.deleteQnA(Exist_User_email, qnADto);

        // then
        QnA qnA = qnARepository.findById(deletedQnA.getId()).get();

        assertEquals(Exist_QnA_id, qnA.getId());
        assertTrue(qnA.getIsDeleted());
    }

    @Test
    @DisplayName("[Service] QnA 검색 테스트")
    public void searchTest() {
        // given
        // when
        List<QnADto> qnADtoList = qnAService.searchQnA(QnA_search_query);

        // then
        boolean isContain = true;
        boolean isDeleted = false;

        for(QnADto foundQnADto : qnADtoList) {
            if (!foundQnADto.getTitle().contains(QnA_search_query) && !foundQnADto.getContent().contains(QnA_search_query)) {
                isContain = false;
                break;
            }
            if(foundQnADto.getIsDeleted().equals(true)) {
                isDeleted = true;
                break;
            }
        }

        assertTrue(isContain);
        assertFalse(isDeleted);
    }

}
