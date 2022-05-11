package toyspringboot.server.TestModule;

import toyspringboot.server.ServerApplicationTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class DomainTest extends ServerApplicationTests {
    public Object test(Object object, Object repositoryObject, String methodName) throws Exception {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(methodName, Object.class);
        return method.invoke(repositoryObject, object);
    }

    public Object test(String object, Object repositoryObject, String methodName) throws Exception {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(methodName, String.class);
        return method.invoke(repositoryObject, object);
    }

    public Object test(Long object, Object repositoryObject, String methodName) throws Exception {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(methodName, Long.class);
        return method.invoke(repositoryObject, object);
    }

    public Object test(boolean object, Object repositoryObject, String methodName) throws Exception {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(methodName, boolean.class);
        return method.invoke(repositoryObject, object);
    }
}
