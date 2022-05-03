package toyspringboot.server.TestDesign.Message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Entity.Messages;
import toyspringboot.server.Domain.Repository.MessageRepository;
import toyspringboot.server.TestDesign.DomainTest;
import java.lang.reflect.InvocationTargetException;

import static toyspringboot.server.Message.MessageConstants.MESSAGE_CONTENT;
import static toyspringboot.server.Message.MessageConstants.MESSAGE_ID;

@SpringBootTest
@Transactional
public class MessageDomainTest extends DomainTest {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    @DisplayName("메시지 생성 jpa 테스트")
    public void createTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
//         MessageConstants.MESSAGE_CONTENT

        Messages message = Messages.builder()
                .message(MESSAGE_CONTENT)
                .build();

        // when
        Messages newMessage = super.createTest(message, messageRepository, "save");

        // then
        super.assertEqualsTest(message.getMessage(), newMessage.getMessage());
    }

    @Test
    @DisplayName("메시지 조회 jpa 테스트")
    public void searchMessageTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        // MessageConstants.MESSAGE_ID

        // when
        Messages newMessage = super.readTest(MESSAGE_ID, messageRepository, "findById");

        // then
        super.assertEqualsTest(MESSAGE_ID, newMessage.getId());
    }
}
