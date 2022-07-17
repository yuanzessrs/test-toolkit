package pers.zengsx.toolkit.test.sdk.base;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.annotation.*;

/**
 * @annotation-name: LiteWebTest
 * @description: goal: Each test class has its own context(support mock-mvc)
 * @author: Mr.Zeng
 * @date: 2022/7/9 22:49
 */
@BaseLiteTest
// Each test class has its own context
@DirtiesContext
@AutoConfigureMockMvc
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LiteWebTest {

    @AliasFor(annotation = BaseLiteTest.class, attribute = "classes")
    Class<?>[] classes() default TestApplication.class;

}
