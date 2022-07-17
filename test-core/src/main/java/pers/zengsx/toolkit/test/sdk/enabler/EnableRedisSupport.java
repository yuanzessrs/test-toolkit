package pers.zengsx.toolkit.test.sdk.enabler;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Import;
import pers.zengsx.toolkit.test.sdk.configurations.EmbeddedRedisServerConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: EnableRedisSupport
 * @description:
 * @author: Mr.Zeng
 * @date: 2021-12-09 17:34:57
 */
@Import(EmbeddedRedisServerConfiguration.class)
@ImportAutoConfiguration(RedisAutoConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRedisSupport {
}
