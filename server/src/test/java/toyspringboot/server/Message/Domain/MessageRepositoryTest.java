package toyspringboot.server.Message.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.AcceptanceTestUtils;
import toyspringboot.server.Domain.Dto.MessageDto;
import toyspringboot.server.Domain.Entity.Messages;
import toyspringboot.server.Domain.Repository.MessageRepository;

import static org.junit.jupiter.api.Assertions.*;
import static toyspringboot.server.Message.MessageConstants.*;

public class MessageRepositoryTest extends AcceptanceTestUtils {
    @Autowired
    private MessageRepository messageRepository;

    @Test
    @DisplayName("메시지 생성 jpa 테스트")
    public void insertMessageTest() {
        // given
        // MessageConstants.MESSAGE_CONTENT

        Messages message = Messages.builder()
                .message(MESSAGE_CONTENT)
                .build();

        // when
        MessageDto messageDto = MessageDto.of(messageRepository.save(message));

        // then
        assertEquals(message.getMessage(), messageDto.getMessage());
    }

    @Test
    @DisplayName("메시지 조회 jpa 테스트")
    public void searchMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID

        // when
        MessageDto messageDto = MessageDto.of(messageRepository.findById(MESSAGE_ID).orElseThrow(IllegalArgumentException::new));

        // then
        assertEquals(MESSAGE_ID, messageDto.getId());
    }


    @Test
    @DisplayName("메시지 수정 jpa 테스트")
    public void updateMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID
        // MessageConstants.MESSAGE_CONTENT

        // when
        Messages foundMessage = messageRepository.findById(MESSAGE_ID).orElseThrow(IllegalArgumentException::new);
        foundMessage.setMessage(MESSAGE_CONTENT);
        messageRepository.save(foundMessage);

        MessageDto targetMessage = MessageDto.of(messageRepository.findById(MESSAGE_ID).orElseThrow(IllegalArgumentException::new));

        // then
        assertEquals(MESSAGE_CONTENT, targetMessage.getMessage());
    }


    @Test
    @DisplayName("메시지 삭제 jpa 테스트")
    public void deleteMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID
        // MessageConstants.MESSAGE_CONTENT

        Messages message = Messages.builder()
                .id(MESSAGE_ID)
                .message(MESSAGE_CONTENT)
                .build();

        // when
        messageRepository.delete(message);

        // then
        assertFalse(messageRepository.findById(message.getId()).isPresent());
    }
}
