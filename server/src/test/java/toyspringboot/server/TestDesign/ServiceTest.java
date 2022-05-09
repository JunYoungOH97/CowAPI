package toyspringboot.server.TestDesign;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyspringboot.server.Domain.Dto.MessageDto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static toyspringboot.server.Message.MessageConstants.MESSAGE_CONTENT;

public class ServiceTest {
    public <S extends Object> S createTest(Object createObject, Object serviceObject, String createMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = serviceObject.getClass();
        Method method = c.getMethod(createMethodName, createObject.getClass());
        return (S) method.invoke(serviceObject, createObject);
    }

    public static <S extends Object> S readTest(Object readId, Object serviceObject, String readMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = serviceObject.getClass();
        Method method = c.getMethod(readMethodName, readId.getClass());
        return (S) method.invoke(serviceObject, readId);
    }

    public static <S extends Object> S updateTest(Object updateId, Object updateObject, Object serviceObject, String updateMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> c = serviceObject.getClass();

        Class<?>[] parameters = new Class[2];
        parameters[0] = Long.class;
        parameters[1] = String.class;

        Method method = c.getMethod(updateMethodName, parameters);
        return (S) method.invoke(serviceObject, updateId, updateObject);
    }

    public static <S extends Object> S deleteTest(Object deleteId, Object serviceObject, String deleteMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> c = serviceObject.getClass();
        Method method = c.getMethod(deleteMethodName, deleteId.getClass());
        return (S) method.invoke(serviceObject, deleteId);
    }
}
