package toyspringboot.server.Notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.NoticeRepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModuleReflection.DomainTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static toyspringboot.server.User.UserTestConstants.Exist_User_email;

import static toyspringboot.server.Notice.NoticeTestConstants.*;


public class NoticeDomainTest extends DomainTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    @DisplayName("[Domain] 공지 생성 테스트")
    public void createTest() {
        // given
        User user = userRepository.findByEmail(Exist_User_email).get();

        Notice notice = Notice.builder()
                .title(Test_Notice_title)
                .content(Test_Notice_content)
                .isDeleted(Test_Notice_isDeleted)
                .createdDate(Test_Notice_createdDate)
                .updatedDate(Test_Notice_updatedDate)
                .deletedDate(Test_Notice_deletedDate)
                .creator(Test_Notice_creator)
                .updater(Test_Notice_updater)
                .user(user)
                .build();

        // when
        Notice newNotice = noticeRepository.save(notice);

        // then
        assertEquals(Test_Notice_title, newNotice.getTitle());
        assertEquals(Test_Notice_content, newNotice.getContent());
        assertEquals(Test_Notice_isDeleted, newNotice.getIsDeleted());
        assertEquals(Test_Notice_createdDate, newNotice.getCreatedDate());
        assertEquals(Test_Notice_updatedDate, newNotice.getUpdatedDate());
        assertEquals(Test_Notice_deletedDate, newNotice.getDeletedDate());
        assertEquals(Test_Notice_creator, newNotice.getCreator());
        assertEquals(Test_Notice_updater, newNotice.getUpdater());
        assertEquals(Exist_User_email, newNotice.getUser().getEmail());
        assertTrue(newNotice.getUser().getAdmin());
    }
    @Test
    @DisplayName("[Domain] 공지 조회 테스트")
    public void readTest() {
        // given
        // when
        Optional<Notice> foundNotice = noticeRepository.findById(Exist_Notice_id);

        // then
        assertTrue(foundNotice.isPresent());
        foundNotice.ifPresent(notice -> assertEquals(Exist_Notice_id, foundNotice.get().getId()));
    }
    
    @Test
    @DisplayName("[Domain] 공지 수정 테스트")
    public void updateTest() {
        // given
        Notice notice = noticeRepository.findById(Exist_Notice_id).get();

        NoticeDto noticeDto = NoticeDto.builder()
                .title(Test_Notice_title)
                .content(Test_Notice_content)
                .isDeleted(Exist_Notice_isDeleted)
                .createdDate(Exist_Notice_createdDate)
                .updatedDate(Test_Notice_updatedDate)
                .deletedDate(Exist_Notice_deletedDate)
                .creator(Exist_Notice_creator)
                .updater(Test_Notice_updater)
                .build();

        // when
        noticeRepository.updateNotice(notice, noticeDto);

        // then
        assertEquals(Exist_Notice_id, notice.getId());
        assertEquals(Test_Notice_title, notice.getTitle());
        assertEquals(Test_Notice_content, notice.getContent());
        assertEquals(Exist_Notice_isDeleted, notice.getIsDeleted());
        assertEquals(Exist_Notice_createdDate, notice.getCreatedDate());
        assertEquals(Test_Notice_updatedDate, notice.getUpdatedDate());
        assertEquals(Exist_Notice_deletedDate, notice.getDeletedDate());
        assertEquals(Exist_Notice_creator, notice.getCreator());
        assertEquals(Test_Notice_updater, notice.getUpdater());
        assertTrue(notice.getUser().getAdmin());
    }
    
    @Test
    @DisplayName("[Domain] 공지 삭제 테스트")
    public void deleteTest() {
        // given
        Notice notice = noticeRepository.findById(Exist_Notice_id).get();

        // when
        noticeRepository.deleteNotice(notice, Test_Notice_updater);

        // then
        assertTrue(notice.getIsDeleted());
        assertTrue(notice.getUser().getAdmin());
    }
}
