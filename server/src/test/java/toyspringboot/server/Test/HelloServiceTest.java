package toyspringboot.server.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyspringboot.server.Domain.Dto.HelloDto;
import toyspringboot.server.Service.HelloService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    private HelloDto helloDto;

    @BeforeEach
    public void setUp() throws Exception {
        HelloDto helloDto = new HelloDto();
    }

    @Test
    @DisplayName("샘플 테스트")
    public void helloServiceTest() {
        // Given
        String m = "Hello Spring!";

        // When
        helloDto = helloService.showHello(m);

        // Then
        assertEquals(helloDto.getMessage(), m);
    }
}
