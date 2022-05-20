package toyspringboot.server.TestModule;

import toyspringboot.server.ServerApplicationTests;

import java.lang.reflect.Method;

public class DomainTest extends ServerApplicationTests {
    public Object test(Object object, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Method method = c.getMethod(methodName, Object.class);
        return method.invoke(repository, object);
    }
}
