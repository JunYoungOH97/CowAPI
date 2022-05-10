package toyspringboot.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ServerApplicationTests {
    private static final int port = 8080;

    public String baseUrl() {
        return "http://localhost:" + port;
    }

    public String baseUrl(String path) {
        return this.baseUrl() + path;
    }
}
