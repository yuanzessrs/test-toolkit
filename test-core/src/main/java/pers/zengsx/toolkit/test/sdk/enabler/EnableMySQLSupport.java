package pers.zengsx.toolkit.test.sdk.enabler;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import pers.zengsx.toolkit.test.sdk.configurations.EmbeddedMySQLConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: EnableMySQLSupport
 * @description:
 * @author: Mr.Zeng
 * @date: 2021-12-09 17:34:57
 */
@Import(EmbeddedMySQLConfiguration.class)
@ImportAutoConfiguration(DataSourceAutoConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableMySQLSupport {
}
