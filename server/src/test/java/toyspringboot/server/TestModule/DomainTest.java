package toyspringboot.server.TestModule;

import toyspringboot.server.ServerApplicationTests;

import java.lang.reflect.Method;

public class DomainTest extends ServerApplicationTests {
    public Object test(Object object, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Method method = c.getMethod(methodName, Object.class);
        return method.invoke(repository, object);
    }

    public Object test(String object, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Method method = c.getMethod(methodName, String.class);
        return method.invoke(repository, object);
    }

    public Object test(Long object, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Method method = c.getMethod(methodName, Long.class);
        return method.invoke(repository, object);
    }

    public Object test(boolean object, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Method method = c.getMethod(methodName, boolean.class);
        return method.invoke(repository, object);
    }

    public Object test(String object1, Object object2, Object repository, String methodName) throws Exception {
        Class<?> c = repository.getClass();
        Class[] methodParam = new Class[2];
        methodParam[0] = String.class;
        methodParam[1] = object2.getClass();
        Method method = c.getMethod(methodName, methodParam);
        return method.invoke(repository, object1, object2);
    }
}
