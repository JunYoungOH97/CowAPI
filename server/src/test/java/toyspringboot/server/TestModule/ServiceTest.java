package toyspringboot.server.TestModule;

import toyspringboot.server.ServerApplicationTests;
import java.lang.reflect.Method;

public class ServiceTest extends ServerApplicationTests {
    public Object test(Object object, Object service, String methodName) throws Exception {
        Class<?> c = service.getClass();
        Method method = c.getMethod(methodName, object.getClass());
        return method.invoke(service, object);
    }
}
