package toyspringboot.server.testDesign;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public interface TestApi {
    void createTest();
//    void readTest();
//    void updateTest();
//    void deleteTest();
}
