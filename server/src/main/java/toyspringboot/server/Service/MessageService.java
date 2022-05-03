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
        Messages message = Messages.builder()
                .message(messageDto.getMessage())
                .build();
        return MessageDto.of(messageRepository.save(message));
    }

    public MessageDto searchMessage(Long id) {
        return MessageDto.of(messageRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public MessageDto updateMessage(Long id, String message) {
        Messages foundMessage = messageRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        foundMessage.setMessage(message);
        messageRepository.save(foundMessage);

        return MessageDto.of(messageRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public boolean deleteMessage(Long id) {
        Messages message = Messages.builder()
                .id(id)
                .build();

        // when
        messageRepository.delete(message);

        return true;
    }

}
