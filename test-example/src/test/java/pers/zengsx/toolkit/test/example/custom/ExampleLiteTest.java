package pers.zengsx.toolkit.test.example.custom;

import org.springframework.core.annotation.AliasFor;
import pers.zengsx.toolkit.test.sdk.base.LiteTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: ExampleLiteTest
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:28
 */
@LiteTest
@BaseExampleLiteTest
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExampleLiteTest {

    @AliasFor(annotation = BaseExampleLiteTest.class, attribute = "value")
    Plugins[] value() default {};

}
