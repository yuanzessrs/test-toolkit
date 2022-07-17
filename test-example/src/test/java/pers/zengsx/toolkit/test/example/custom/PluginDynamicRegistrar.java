package pers.zengsx.toolkit.test.example.custom;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @class-name: PluginDynamicRegistrar
 * @description: 动态加载组件
 * @author: Mr.Zeng
 * @date: 2022-07-11 15:19
 */
public class PluginDynamicRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BaseExampleLiteTest.class.getName());
        assert annotationAttributes != null;
        Plugins[] plugins = (Plugins[]) annotationAttributes.get("value");
        Set<Plugins> pluginsFinal = new HashSet<>(Arrays.asList(plugins));
        if (pluginsFinal.isEmpty()) {
            return;
        }

        Set<Plugins> dependentPlugins = pluginsFinal.stream()
                .flatMap(p -> p.getDependentPlugins().stream())
                .collect(Collectors.toSet());
        pluginsFinal.addAll(dependentPlugins);

        //扫描类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        Set<String> paths = new HashSet<>();
        for (Plugins plugin : pluginsFinal) {
            Class<?> setupClass = plugin.getSetupClass();
            scanner.addIncludeFilter(new AssignableTypeFilter(setupClass));
            paths.add(setupClass.getPackage().getName());
        }
        scanner.scan(paths.toArray(new String[]{}));
    }

}
