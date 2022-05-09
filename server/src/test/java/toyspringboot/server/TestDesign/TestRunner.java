package toyspringboot.server.TestDesign;

import java.lang.reflect.InvocationTargetException;

public class TestRunner {
    private final String featureName;
//    private final TestAPI domainTest;
    private final TestAPI serviceTest;

    public TestRunner(String featureName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.featureName = featureName;
//        this.domainTest = Class.forName("toyspringboot.server.TestDesign." + this.featureName + "." + this.featureName + "DomainTest").getConstructor().newInstance();
        this.serviceTest = (TestAPI) Class.forName("toyspringboot.server.TestDesign." + this.featureName + "." + this.featureName + "ServiceTest").getConstructor().newInstance();

        Class<?> c = Class.forName("toyspringboot.server.TestDesign." + this.featureName + "." + this.featureName + "ServiceTest");
        c.cast(this.serviceTest);

        System.out.println("featureName = " + this.serviceTest.getClass().toString());
    }

//    public void runDomainTest() {
//        this.domainTest.featureCreateTest();
//        this.domainTest.readTest();
//        this.domainTest.updateTest();
//        this.domainTest.deleteTest();
//    }

    public void runServiceTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        this.serviceTest.createTest();
        this.serviceTest.readTest();
        this.serviceTest.updateTest();
        this.serviceTest.deleteTest();
    }
}
