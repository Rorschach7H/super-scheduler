package com.meixiaoxi.scheduler.spi;

public class TestServiceImpl implements TestService {

    public TestServiceImpl() {
        System.out.println("1111111");
    }

    @Override
    public void sayHello() {
        System.out.println("1");
    }

}
