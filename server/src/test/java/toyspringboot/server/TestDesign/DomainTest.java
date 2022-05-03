package toyspringboot.server.TestDesign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainTest {
    public <S extends Object> S createTest(Object obj, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, Object.class);
        return (S) method.invoke(instance, obj);
    }

    public <S extends Object> S readTest(Long id, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, Object.class);
        Optional<Object> m = (Optional<Object>) method.invoke(instance, id);
        return (S) m.orElseThrow(IllegalArgumentException::new);
    }

    public void assertEqualsTest(Object expect, Object actual) {
        assertEquals(expect, actual);
    }
}
