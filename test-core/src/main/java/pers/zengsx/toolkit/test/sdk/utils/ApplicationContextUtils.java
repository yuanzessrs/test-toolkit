package pers.zengsx.toolkit.test.sdk.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @class-name: ApplicationContextUtils
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-11 16:03
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context = null;

    /**
     * 获取bean
     *
     * @param clazz bean class
     * @return bean 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 获取bean
     *
     * @param clazz bean class
     * @return bean 实例
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }

    /**
     * 列举容器中所有bean names
     *
     * @return all bean names
     */
    public static List<String> allBeanNames() {
        return Arrays.asList(context.getBeanDefinitionNames());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
