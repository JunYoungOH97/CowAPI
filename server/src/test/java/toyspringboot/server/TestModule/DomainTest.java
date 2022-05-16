package toyspringboot.server.TestModule;

import toyspringboot.server.ServerApplicationTests;

import java.lang.reflect.Method;

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

    public Object test(Long object1, Object object2, Object repositoryObject, String methodName) throws Exception {
        Class<?> c = repositoryObject.getClass();
        Class[] methodParam = new Class[2];
        methodParam[0] = Long.class;
        methodParam[1] = object2.getClass();
        Method method = c.getMethod(methodName, methodParam);
        return method.invoke(repositoryObject, object1, object2);
    }

}
