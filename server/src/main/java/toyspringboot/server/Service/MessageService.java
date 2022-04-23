package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyspringboot.server.Domain.Dto.MessageDto;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final MessageRepository helloRepository;

    public MessageDto showHello(String m) {
        return MessageDto.builder().message(m).build();
    }

    public MessageDto getMessage(Long id) {
        return helloRepository.get(id);
    }

    public boolean postMessage(MessageDto helloDto) {
        return helloRepository.insert(helloDto);
    }

    public boolean putMessage(Long id, String message) {
        return helloRepository.put(id, message);
    }

    public boolean deleteMessage(Long id) {
        return helloRepository.delete(id);
    }
}
