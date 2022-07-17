package pers.zengsx.toolkit.test.sdk.registrar.mybatis;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

import static org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_NAME;

/**
 * @class-name: MybatisPlusMapperDynamicRegistrar
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-11 16:55
 */
@Slf4j
public class MybatisPlusMapperDynamicRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attrs = metadata.getAnnotationAttributes(LoadMybatisMapper.class.getName());
        assert attrs != null;
        Class<?>[] mappers = (Class<?>[]) attrs.get("classes");
        for (Class<?> mapper : mappers) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(MapperFactoryBean.class);
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, mapper);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            beanDefinition.setDependsOn("sqlSessionFactory");
            beanDefinition.setAutowireMode(AUTOWIRE_BY_NAME);

            String beanName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, mapper.getSimpleName());
            log.info("register mybatis mapper, className: {}, beanName: {}", mapper.getName(), beanName);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }


    @AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
    public static class Loader {

    }

}
