package pers.zengsx.toolkit.test.sdk.registrar.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @@interface-name: LoadMybatisMapper
 * @description: 按需手动加载 mybatis-plus mapper；统一扫描的会增加测试用例整体耗时【发生在spring容器的初始化和销毁】
 * @author: Mr.Zeng
 * @date: 2022-07-11 16:56
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MybatisPlusMapperDynamicRegistrar.class)
public @interface LoadMybatisMapper {

    Class<?>[] classes();

}
