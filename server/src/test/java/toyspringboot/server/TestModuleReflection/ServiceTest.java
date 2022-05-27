package toyspringboot.server.TestModuleReflection;

import toyspringboot.server.ServerApplicationTests;
import java.lang.reflect.Method;

public class ServiceTest extends ServerApplicationTests {
    public Object test(Object object, Object service, String methodName) throws Exception {
        Class<?> c = service.getClass();
        Method method = c.getMethod(methodName, object.getClass());
        return method.invoke(service, object);
    }

    public Object test(String userToken, Object object, Object service, String methodName) throws Exception {
        Class<?> c = service.getClass();
        Class[] methodParam = new Class[2];
        methodParam[0] = String.class;
        methodParam[1] = object.getClass();
        Method method = c.getMethod(methodName, methodParam);
        return method.invoke(service, userToken, object);
    }

    public Object test(Long object1, Object object2, Object service, String methodName) throws Exception {
        Class<?> c = service.getClass();
        Class[] methodParam = new Class[2];
        methodParam[0] = Long.class;
        methodParam[1] = object2.getClass();
        Method method = c.getMethod(methodName, methodParam);
        return method.invoke(service, object1, object2);
    }
}
