package toyspringboot.server.QnA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.QnA.QnATestConstants.*;
import static toyspringboot.server.QnA.QnATestConstants.Create_Date;
import static toyspringboot.server.QnA.QnATestConstants.Creator_Member;
import static toyspringboot.server.User.UserTestConstants.*;

public class QnADomainTest  extends DomainTest {
    @Autowired
    private QnARepository qnARepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("[Domain] QnA 생성 테스트")
    public void createTest() throws Exception {
        // given
        User user = userRepository.findByEmail(Exist_User_email).get();

        QnA qnA = QnA.builder()
                .title(QnA_title)
                .isDeleted(QnA_isDeleted)
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .user(user)
                .build();

        // when
        QnA newQnA = (QnA) test(qnA, qnARepository, "save");

        // then
        assertEquals(qnA.getTitle(), newQnA.getTitle());
        assertEquals(user.getQnAs().get(QnA_Index).getId(), newQnA.getId());
    }

    @Test
    @DisplayName("[Domain] QnA 조회 테스트")
    public void updateTest() throws Exception {
        // given
        // when
        Optional<QnA> foundQnA = (Optional<QnA>) test(Exist_QnA_id, qnARepository, "findById");

        // then (expect, actual)
        assertTrue(foundQnA.isPresent());
        foundQnA.ifPresent(qnA -> assertEquals(Exist_QnA_id, qnA.getId()));
    }

    @Test
    @DisplayName("[Domain] QnA query 조회 테스트")
    public void searchTest() {
        // given
        // when
        List<QnA> qnAList =  qnARepository.searchByQuery(QnA_search_query);

        // then
        boolean isContain = true;
        boolean isDeleted = false;
        for(QnA foundQnA : qnAList) {
            if (!foundQnA.getTitle().contains(QnA_search_query) &&
                !foundQnA.getContent().contains(QnA_search_query)) {
                isContain = false;
                break;
            }
            if(foundQnA.getIsDeleted().equals(true)) {
                isDeleted = true;
                break;
            }
        }

        assertTrue(isContain);
        assertFalse(isDeleted);
    }

    @Test
    @DisplayName("[Domain] QnA 페이지 네이션")
    public void QnAPageTest() {
        // given

        // when
        List<QnA> qnAList = qnARepository.findByPage(QnA_Page);

        // then
        boolean isCnt = true;
        boolean isOrdered = true;

        if(qnAList.size() > 5) {
            isCnt = false;
        }

        for(int i = 0; i < qnAList.size() - 1; i++) {
            if(qnAList.get(i).getUpdatedDate().toLocalDateTime().isAfter(qnAList.get(i + 1).getUpdatedDate().toLocalDateTime())) {
                isOrdered = false;
                break;
            }
        }

        assertTrue(isCnt);
        assertTrue(isOrdered);
    }
}
