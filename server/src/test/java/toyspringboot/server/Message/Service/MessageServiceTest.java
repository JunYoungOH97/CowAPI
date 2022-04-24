package toyspringboot.server.Message.Service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import toyspringboot.server.AcceptanceTestUtils;
import toyspringboot.server.Domain.Dto.MessageDto;
import toyspringboot.server.Service.MessageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.Message.MessageConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class MessageServiceTest extends AcceptanceTestUtils {
    @Autowired
    private MessageService messageService;

    @Test
    @DisplayName("메시지 생성 테스트")
    public void createMessageTest() {
        // given
        // MessageConstants.MESSAGE_CONTENT

        MessageDto messageDto = MessageDto.builder()
                .message(MESSAGE_CONTENT)
                .build();

        // when
        MessageDto createdMessage = messageService.createMessage(messageDto);

        // then
        assertEquals(MESSAGE_CONTENT, createdMessage.getMessage());
    }

    @Test
    @DisplayName("메시지 조회 테스트")
    public void searchMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID
        // when
        MessageDto searchedMessage = messageService.searchMessage(MESSAGE_ID);

        // then
        assertEquals(MESSAGE_ID, searchedMessage.getId());
    }


    @Test
    @DisplayName("메시지 수정 테스트")
    public void updateMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID
        // MessageConstants.MESSAGE_CONTENT

        // when
        MessageDto updatedMessage = messageService.updateMessage(MESSAGE_ID, MESSAGE_CONTENT);

        // then
        assertEquals(MESSAGE_CONTENT, updatedMessage.getMessage());
    }

    @Test
    @DisplayName("메시지 삭제 테스트")
    public void deleteMessageTest() {
        // given
        // MessageConstants.MESSAGE_ID

        // when, then
        assertTrue(messageService.deleteMessage(MESSAGE_ID));
    }
}
