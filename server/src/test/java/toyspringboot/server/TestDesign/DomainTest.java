package toyspringboot.server.TestDesign;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

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

    public <S extends Object> S updateTest(Long id, Object instance, String methodName,Object o2, String content, String method2, String method3) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        S foundObject = this.readTest(id, instance, methodName);
        Class<?> c = o2.getClass();
        o2.getClass().cast(foundObject);
        Method method = c.getMethod(method3, String.class);
        method.invoke(foundObject, content);

        return this.createTest(foundObject, instance, method2);
    }

    public void deleteTest(Object obj, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, Object.class);
        method.invoke(instance, obj);
    }
}
