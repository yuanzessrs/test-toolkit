package pers.zengsx.toolkit.test.example.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pers.zengsx.toolkit.test.example.custom.configuration.CustomMySQLConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @enum-name: Plugins
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/13 19:07
 */
@AllArgsConstructor
@Getter
public enum Plugins {


    DAO(CustomMySQLConfiguration.class),
//
//    REDIS(XXXRedisSupportConfiguration.class),
//
//    CACHE(XXXCacheConfiguration.class, REDIS),
//
//    LOCK(XXXConfiguration.class, REDIS),
//
//    KV(XXXConfiguration.class),

    ;

    private final Class<?> setupClass;

    private final List<Plugins> dependentPlugins;

    Plugins(Class<?> setupClass) {
        this.setupClass = setupClass;
        this.dependentPlugins = Collections.emptyList();
    }

    Plugins(Class<?> setupClass, Plugins... dependentPlugins) {
        this.setupClass = setupClass;
        this.dependentPlugins = Arrays.asList(dependentPlugins);
    }


}
