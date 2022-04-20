package toyspringboot.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyspringboot.server.Domain.Dto.HelloDto;

@Service
@RequiredArgsConstructor
public class HelloService {

    public HelloDto showHello(String m) {
        HelloDto helloDto = HelloDto.builder()
                .message(m)
                .build();

        return helloDto;
    }

}
