package pers.zengsx.toolkit.test.example.custom;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: BaseExampleLiteTest
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:30
 */
@Import(PluginDynamicRegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseExampleLiteTest {

    Plugins[] value() default {};

}
