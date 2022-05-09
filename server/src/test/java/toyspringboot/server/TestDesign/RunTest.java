package toyspringboot.server.TestDesign;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;


@SpringBootTest
@Transactional
public class RunTest {
    private TestRunner testRunner;

    @Test
    public void MessageServiceTest() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.testRunner = new TestRunner("Message");
        this.testRunner.runServiceTest();
    }
}
