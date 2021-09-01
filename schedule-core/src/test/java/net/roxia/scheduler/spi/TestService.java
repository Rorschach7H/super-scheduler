package net.roxia.scheduler.spi;

/**
 * @author Robert HG (254963746@qq.com) on 5/18/15.
 */
@SPI(dynamicKey = "testService", defaultName = "service1")
public interface TestService {
    public void sayHello();
}
