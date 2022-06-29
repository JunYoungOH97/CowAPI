package toyspringboot.server.Notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Service.NoticeService;
import toyspringboot.server.TestModuleReflection.ServiceTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import static toyspringboot.server.Notice.NoticeSuccessConstants.*;
import static toyspringboot.server.User.UserTestConstants.*;

@SpringBootTest
@Transactional
public class NoticeServiceTest extends ServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    @DisplayName("[Service] 공지 생성 테스트")
    public void createTest() {
        // given
        NoticeDto noticeDto = NoticeDto.builder()
                .title(Test_Notice_title)
                .content(Test_Notice_content)
                .build();

        // when
        NoticeDto newNoticeDto = noticeService.createNotice(Exist_User_email, noticeDto);

        // then
        assertEquals(Exist_User_email, newNoticeDto.getUserDto().getEmail());
        assertEquals(Test_Notice_title, newNoticeDto.getTitle());
        assertEquals(Test_Notice_content, newNoticeDto.getContent());
//        assertEquals(Test_Notice_createdDate, newNoticeDto.getCreatedDate());
//        assertEquals(Test_Notice_creator, newNoticeDto.getCreator());
//        assertEquals(Test_Notice_updatedDate, newNoticeDto.getUpdatedDate());
//        assertEquals(Test_Notice_updater, newNoticeDto.getUpdater());
        assertTrue(newNoticeDto.getUserDto().getAdmin());
    }

    @Test
    @DisplayName("[Service] 공지 조회 테스트")
    public void readTest() {
        // given
        // when
        NoticeDto foundNoticeDto = noticeService.readNotice(Exist_User_email, Exist_Notice_id);

        // then
        assertEquals(Exist_Notice_id, foundNoticeDto.getId());
        assertEquals(Exist_User_email, foundNoticeDto.getUserDto().getEmail());
        assertEquals(Exist_Notice_title, foundNoticeDto.getTitle());
        assertEquals(Exist_Notice_content, foundNoticeDto.getContent());
        assertEquals(Exist_Notice_isDeleted, foundNoticeDto.getIsDeleted());
//        assertEquals(Exist_Notice_createdDate, foundNoticeDto.getCreatedDate());
//        assertEquals(Exist_Notice_updatedDate, foundNoticeDto.getUpdatedDate());
//        assertEquals(Exist_Notice_deletedDate, foundNoticeDto.getDeletedDate());
        assertEquals(Exist_Notice_creator, foundNoticeDto.getCreator());
        assertEquals(Exist_Notice_updater, foundNoticeDto.getUpdater());
    }
    
    @Test
    @DisplayName("[Service] 공지 수정 테스트")
    public void updateTest() {
        // given
        NoticeDto noticeDto = NoticeDto.builder()
                .id(Exist_Notice_id)
                .title(Test_Notice_title)
                .content(Test_Notice_content)
                .updatedDate(Test_Notice_updatedDate)
                .updater(Test_Notice_updater)
                .build();

        // when
        NoticeDto updatedNotice = noticeService.updateNotice(Exist_User_email, noticeDto);

        // then
        assertEquals(Exist_Notice_id, updatedNotice.getId());
        assertEquals(Exist_User_email, updatedNotice.getUserDto().getEmail());
        assertEquals(Test_Notice_title, updatedNotice.getTitle());
        assertEquals(Test_Notice_content, updatedNotice.getContent());
        assertEquals(Exist_Notice_isDeleted, updatedNotice.getIsDeleted());
//        assertEquals(Exist_Notice_createdDate, updatedNotice.getCreatedDate());
//        assertEquals(Test_Notice_updatedDate, updatedNotice.getUpdatedDate());
//        assertEquals(Exist_Notice_deletedDate, updatedNotice.getDeletedDate());
        assertEquals(Exist_Notice_creator, updatedNotice.getCreator());
        assertEquals(Test_Notice_updater, updatedNotice.getUpdater());
        assertTrue(updatedNotice.getUserDto().getAdmin());
    }
    
    @Test
    @DisplayName("[Service] 공지 삭제 테스트")
    public void deleteTest() {
        // given
        // when
        NoticeDto deletedNotice = noticeService.deleteNotice(Exist_Notice_user, Exist_Notice_id, Test_Notice_updater);

        // then
        assertEquals(Exist_Notice_id, deletedNotice.getId());
        assertEquals(Exist_User_email, deletedNotice.getUserDto().getEmail());
        assertEquals(Exist_Notice_title, deletedNotice.getTitle());
        assertEquals(Exist_Notice_content, deletedNotice.getContent());
        assertEquals(Test_Notice_isDeleted, deletedNotice.getIsDeleted());
//        assertEquals(Exist_Notice_createdDate, deletedNotice.getCreatedDate());
//        assertEquals(Exist_Notice_updatedDate, deletedNotice.getUpdatedDate());
//        assertEquals(Test_Notice_deletedDate, deletedNotice.getDeletedDate());
        assertEquals(Exist_Notice_creator, deletedNotice.getCreator());
        assertEquals(Test_Notice_updater, deletedNotice.getUpdater());
        assertTrue(deletedNotice.getUserDto().getAdmin());
    }
    
}
