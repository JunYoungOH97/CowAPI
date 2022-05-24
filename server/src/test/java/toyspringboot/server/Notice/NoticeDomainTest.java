package toyspringboot.server.Notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Entity.Notice;
import toyspringboot.server.Domain.Entity.QnA;
import toyspringboot.server.Domain.Entity.User;
import toyspringboot.server.Domain.Repository.NoticeRepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.TestModule.DomainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.DefaultTestConstants.Create_Date;
import static toyspringboot.server.DefaultTestConstants.Creator_Member;
import static toyspringboot.server.QnA.QnATestConstants.*;
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
                .createdDate(Create_Date)
                .creator(Creator_Member)
                .updatedDate(Update_Date)
                .updater(Update_Member)
                .isDeleted(false)
                .user(user)
                .build();

        // when
        Notice newNotice = noticeRepository.save(notice);

        // then
        assertEquals(Test_Notice_title, newNotice.getTitle());
        assertEquals(Test_Notice_content, newNotice.getContent());
        assertEquals(Exist_User_email, newNotice.getUser().getEmail());
    }
    
    @Test
    @DisplayName("[Domain] 공지 수정 테스트")
    public void updateTest() {
        // given
        Notice notice = noticeRepository.findById(Exist_Notice_id).get();

        NoticeDto noticeDto = NoticeDto.builder()
                .title(Test_Notice_title)
                .content(Test_Notice_content)
                .build();

        // when
        Notice updatedNotice = noticeRepository.updateNotice(notice, noticeDto);

        // then
        assertEquals(Test_Notice_title, updatedNotice.getTitle());
        assertEquals(Test_Notice_content, updatedNotice.getContent());
    }
    
}
