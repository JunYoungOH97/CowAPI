package toyspringboot.server.Notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.Domain.Dto.NoticeDto;
import toyspringboot.server.Domain.Repository.NoticeRepository;
import toyspringboot.server.Domain.Repository.QnARepository;
import toyspringboot.server.Domain.Repository.UserRepository;
import toyspringboot.server.Service.QnAService;
import toyspringboot.server.TestModule.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import static toyspringboot.server.Notice.NoticeTestConstants.*;
import static toyspringboot.server.User.UserTestConstants.*;

public class NoticeServiceTest extends ServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

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
        assertEquals(Test_Notice_title, newNoticeDto.getTitle());
        assertEquals(Test_Notice_content, newNoticeDto.getContent());
        assertEquals(Exist_User_email, newNoticeDto.getUserDto().getEmail());
    }

}
