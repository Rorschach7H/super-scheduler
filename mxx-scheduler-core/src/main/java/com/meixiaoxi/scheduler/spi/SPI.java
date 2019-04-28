package com.meixiaoxi.scheduler.spi;

import java.lang.annotation.*;

/**
 * 参考lts中的spi实现机制
 *
 * @author Robert HG (254963746@qq.com)on 12/23/15.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * 默认扩展实现
     */
    String defaultValue() default "";

}
