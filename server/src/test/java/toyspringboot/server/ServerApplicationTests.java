package toyspringboot.server;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ServerApplicationTests {
    @LocalServerPort
    protected int port;

    private static final String version = "/api/v1";

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    protected String baseUrl(String path) {
        return this.baseUrl() + version + path;
    }
}
