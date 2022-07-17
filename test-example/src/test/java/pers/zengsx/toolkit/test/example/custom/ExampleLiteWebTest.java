package pers.zengsx.toolkit.test.example.custom;

import org.springframework.core.annotation.AliasFor;
import pers.zengsx.toolkit.test.sdk.base.LiteWebTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: ExampleLiteWebTest
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:28
 */
@LiteWebTest
@BaseExampleLiteTest
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExampleLiteWebTest {

    @AliasFor(annotation = BaseExampleLiteTest.class, attribute = "value")
    Plugins[] value() default {};

}
