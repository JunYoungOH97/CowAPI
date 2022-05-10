package toyspringboot.server;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ServerApplicationTests {
    @LocalServerPort
    public int port;

    public String baseUrl() {
        return "http://localhost:" + port;
    }

    public String baseUrl(String path) {
        return this.baseUrl() + path;
    }
}
