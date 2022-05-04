package toyspringboot.server.TestDesign;

import toyspringboot.server.Domain.Dto.MessageDto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.Message.MessageConstants.MESSAGE_CONTENT;

public class ServiceTest {
    public <S extends Object> S createTest(Object obj, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, obj.getClass());
        return (S) method.invoke(instance, obj);
    }

    public <S extends Object> S readTest(Long id, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, id.getClass());
        return (S) method.invoke(instance, id);
    }

    public <S extends Object> S updateTest(Long id, String content, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> c = instance.getClass();

        Class parameters[] = new Class[2];
        parameters[0] = Long.class;
        parameters[1] = String.class;

        Method method = c.getMethod(methodName, parameters);
        return (S) method.invoke(instance, id, content);
    }


    public boolean deleteTest(Long id, Object instance, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> c = instance.getClass();
        Method method = c.getMethod(methodName, id.getClass());
        return true;
    }
}
