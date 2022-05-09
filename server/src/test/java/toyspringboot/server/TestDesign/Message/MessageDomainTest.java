package toyspringboot.server.TestDesign.Message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Repository.MessageRepository;
import toyspringboot.server.Domain.Entity.Messages;
import toyspringboot.server.TestDesign.DomainTest;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.Message.MessageConstants.MESSAGE_CONTENT;
import static toyspringboot.server.Message.MessageConstants.MESSAGE_ID;

@SpringBootTest
@Transactional
public class MessageDomainTest extends DomainTest {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    @DisplayName("메시지 생성 jpa 테스트")
    public void featureCreateTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
//         MessageConstants.MESSAGE_CONTENT

        Messages message = Messages.builder()
                .message(MESSAGE_CONTENT)
                .build();

        // when
        Messages newMessage = super.createTest(message, messageRepository, "save");

        // then
        assertEquals(message.getMessage(), newMessage.getMessage());
    }

    @Test
    @DisplayName("메시지 조회 jpa 테스트")
    public void featureSearchTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        // MessageConstants.MESSAGE_ID

        // when
        Messages newMessage = super.readTest(MESSAGE_ID, messageRepository, "findById");

        // then
        assertEquals(MESSAGE_ID, newMessage.getId());
    }


    @Test
    @DisplayName("메시지 수정 jpa 테스트")
    public void featureUpdateTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        // given
        // MessageConstants.MESSAGE_ID
        // MessageConstants.MESSAGE_CONTENT

        // when
        Messages newMessage = super.updateTest(MESSAGE_ID, Messages.class, messageRepository, "findById", MESSAGE_CONTENT, "save", "setMessage");

        // then
        assertEquals(MESSAGE_CONTENT, newMessage.getMessage());
    }

    @Test
    @DisplayName("메시지 삭제 jpa 테스트")
    public void featureDeleteTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        // given
        // MessageConstants.MESSAGE_ID
        // MessageConstants.MESSAGE_CONTENT

        Messages message = Messages.builder()
                .id(MESSAGE_ID)
                .message(MESSAGE_CONTENT)
                .build();

        // when
        super.deleteTest(message, messageRepository, "delete");

        // then
        try {
            super.readTest(MESSAGE_ID, messageRepository, "findById");
            assertTrue(false);

        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }
}
