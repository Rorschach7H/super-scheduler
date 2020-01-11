package net.roxia.scheduler.spi;

public class TestServiceImpl2 implements TestService {

    public TestServiceImpl2() {
        System.out.println("2222222");
    }

    @Override
    public void sayHello() {
        System.out.println("2");
    }
}
