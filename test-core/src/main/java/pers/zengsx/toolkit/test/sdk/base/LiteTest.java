package pers.zengsx.toolkit.test.sdk.base;

import org.springframework.test.annotation.DirtiesContext;

import java.lang.annotation.*;

/**
 * @annotation-name: LiteTest
 * @description: goal: Each test class has its own context
 * @author: Mr.Zeng
 * @date: 2022/7/9 23:53
 */
@BaseLiteTest
// Each test class has its own context
@DirtiesContext
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LiteTest {

}
