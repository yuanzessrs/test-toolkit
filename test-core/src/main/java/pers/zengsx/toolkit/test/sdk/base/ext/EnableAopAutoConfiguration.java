package pers.zengsx.toolkit.test.sdk.base.ext;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: EnableAopAutoConfiguration
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/10 15:00
 */
// 必须加，springboot自动扫描的时候会扫到这个AOP的自动配置类(默认cglib代理)
// 因为test测试容器是轻量的，所以不会包含这个配置，就会导致aop走jdk动态代理
@Import(AopAutoConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAopAutoConfiguration {
}
