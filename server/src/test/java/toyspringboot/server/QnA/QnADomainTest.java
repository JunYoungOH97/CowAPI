//package toyspringboot.server.QnA;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.server.ResponseStatusException;
//import toyspringboot.server.Domain.Dto.QnADto;
//import toyspringboot.server.Domain.Entity.QnA;
//import toyspringboot.server.Domain.Entity.User;
//import toyspringboot.server.Domain.Repository.QnARepository;
//import toyspringboot.server.Domain.Repository.UserRepository;
//import toyspringboot.server.ServerApplicationTests;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.http.HttpStatus.NOT_FOUND;
//import static toyspringboot.server.QnA.QnATestConstants.*;
//import static toyspringboot.server.User.UserTestConstants.*;
//
//public class QnADomainTest  extends ServerApplicationTests {
//    @Autowired
//    private QnARepository qnARepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @DisplayName("[Domain] QnA 생성 테스트")
//    public void createTest() {
//        // given
//        User user = userRepository.findByEmail(Exist_User_email).orElseThrow();
//
//        QnA qnA = QnA.builder()
//                .title(QnA_title)
//                .isDeleted(QnA_isDeleted)
//                .createdDate(Create_Date)
//                .creator(Creator_Member)
//                .updatedDate(Create_Date)
//                .updater(Creator_Member)
//                .user(user)
//                .build();
//
//        // when
//        QnA newQnA = qnARepository.save(qnA);
//
//        // then
//        assertEquals(qnA.getTitle(), newQnA.getTitle());
//    }
//
//    @Test
//    @DisplayName("[Domain] QnA 조회 테스트")
//    public void readTest() {
//        // given
//        // when
//        QnA foundQnA = qnARepository.findById(Exist_QnA_id).orElseThrow();
//
//        // then (expect, actual)
//        assertEquals(Exist_QnA_id, foundQnA.getId());
//    }
//
//    @Test
//    @DisplayName("[Domain] QnA query 조회 테스트")
//    public void searchTest() {
//        // given
//        // when
//        List<QnA> qnAList =  qnARepository.searchByQuery(QnA_search_query, QnA_search_cnt);
//
//        // then
//        boolean isContain = true;
//        boolean isDeleted = false;
//        boolean isCnt = true;
//
//        for(QnA foundQnA : qnAList) {
//            if (!foundQnA.getTitle().contains(QnA_search_query) &&
//                !foundQnA.getContent().contains(QnA_search_query)) {
//                isContain = false;
//                break;
//            }
//            if(foundQnA.getIsDeleted().equals(true)) {
//                isDeleted = true;
//                break;
//            }
//        }
//
//        if(qnAList.size() > QnA_search_cnt) {
//            isCnt = false;
//        }
//
//        assertTrue(isContain);
//        assertFalse(isDeleted);
//        assertTrue(isCnt);
//    }
//
//    @Test
//    @DisplayName("[Domain] QnA 페이지 네이션")
//    public void QnAPageTest() {
//        // given
//        final Long startIndex = (QnA_Page - 1L) * QnA_Page_cnt;
//
//        // when
//        List<QnA> qnAList = qnARepository.findByPage(startIndex, QnA_Page_cnt);
//
//        // then
//        boolean isCnt = true;
//        boolean isOrdered = true;
//        boolean isDeleted = false;
//
//        if(qnAList.size() > QnA_Page_cnt) {
//            isCnt = false;
//        }
//
//        for(int i = 0; i < qnAList.size() - 1; i++) {
//            if(qnAList.get(i).getUpdatedDate().toLocalDateTime().isAfter(qnAList.get(i + 1).getUpdatedDate().toLocalDateTime())) {
//                isOrdered = false;
//                break;
//            }
//            if(qnAList.get(i).getIsDeleted()) {
//                isDeleted = true;
//                break;
//            }
//        }
//
//        assertTrue(isCnt);
//        assertTrue(isOrdered);
//        assertFalse(isDeleted);
//    }
//
//    @Test
//    @DisplayName("[Domain] QnA 수정 테스트")
//    public void updateTest() {
//        // given
//        QnA qnA = qnARepository.findById(Exist_QnA_id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다."));
//        QnADto qnADto = QnADto.builder()
//                .id(Exist_QnA_id)
//                .title(QnA_title)
//                .content(QnA_content)
//                .build();
//
//        // when
//        qnARepository.updateQnA(qnA, qnADto);
//
//        // then
//        assertEquals(QnA_title, qnA.getTitle());
//        assertEquals(QnA_content, qnA.getContent());
//    }
//
//    @Test
//    @DisplayName("[Domain] QnA 삭제 테스트")
//    public void deleteTest() {
//        // given
//        QnA qnA = qnARepository.findById(Exist_QnA_id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 QnA 입니다."));
//
//        // when
//        qnARepository.deleteQnA(qnA);
//
//        //then
//        assertTrue(qnA.getIsDeleted());
//    }
//}
