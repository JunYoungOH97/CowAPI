package toyspringboot.server.TestModule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class DomainTest {
    public <S extends Object> S createTest(Object obj, Object repositoryObject, String saveMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(saveMethodName, Object.class);
        return (S) method.invoke(repositoryObject, obj);
    }

    public <S extends Object> S readTest(Object readId, Object repositoryObject, String readMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = repositoryObject.getClass();
        Method method = c.getMethod(readMethodName, Object.class);
        Optional<Object> m = (Optional<Object>) method.invoke(repositoryObject, readId);
        return (S) m.orElseThrow(IllegalArgumentException::new);
    }

    public <S extends Object> S updateTest(Object updateId, Class<?> entityClass, Object repositoryObject, String readMethodName, Object updateObj, String saveMethodName, String setterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        S foundObject = this.readTest(updateId, repositoryObject, readMethodName);
        entityClass.cast(foundObject);
        Method method = entityClass.getMethod(setterName, updateObj.getClass());
        method.invoke(foundObject, updateObj);

        return this.createTest(foundObject, repositoryObject, saveMethodName);
    }

    public void deleteTest(Object obj, Object repositoryName, String deleteMethodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = repositoryName.getClass();
        Method method = c.getMethod(deleteMethodName, Object.class);
        method.invoke(repositoryName, obj);
    }
}