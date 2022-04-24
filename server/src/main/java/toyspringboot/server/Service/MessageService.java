package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Dto.MessageDto;
import toyspringboot.server.Domain.Entity.Messages;
import toyspringboot.server.Domain.Repository.MessageRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageDto createMessage(MessageDto messageDto) {
        return messageDto;
    }

    public MessageDto searchMessage(Long id) {
        return MessageDto.builder().id(id).build();
    }

    public MessageDto updateMessage(Long id, String message) {
        return MessageDto.builder()
                .id(id)
                .message(message)
                .build();
    }

    public MessageDto deleteMessage(Long id) {
        return MessageDto.builder()
                .id(id)
                .build();
    }

}
