package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyspringboot.server.Domain.Dto.HelloDto;
import toyspringboot.server.Domain.Repository.HelloRepository;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloRepository helloRepository;

    public HelloDto showHello(String m) {
        return HelloDto.builder().message(m).build();
    }

    public HelloDto getHello(Long id) {
        return helloRepository.get(id);
    }

    public boolean postHello(HelloDto helloDto) {
        return helloRepository.insert(helloDto);
    }

    public boolean putMessage(Long id, String message) {
        return helloRepository.put(id, message);
    }

    public boolean deleteMessage(Long id) {
        return helloRepository.delete(id);
    }
}
