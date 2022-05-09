package toyspringboot.server.TestDesign;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

public interface TestAPI {
    void createTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
    void readTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
    void updateTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
    void deleteTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
}
