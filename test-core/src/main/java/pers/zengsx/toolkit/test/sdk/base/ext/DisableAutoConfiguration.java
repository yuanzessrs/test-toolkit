package pers.zengsx.toolkit.test.sdk.base.ext;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: DisableAutoConfiguration
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/10 15:00
 */
// 关闭自动装配，按需加载
@TestPropertySource(properties = {EnableAutoConfiguration.ENABLED_OVERRIDE_PROPERTY + "=false"})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableAutoConfiguration {
}
