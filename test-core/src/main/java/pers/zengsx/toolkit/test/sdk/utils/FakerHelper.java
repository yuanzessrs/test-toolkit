package pers.zengsx.toolkit.test.sdk.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @class-name: FakerHelper
 * @description: reference: https://github.com/DiUS/java-faker
 * @author: Mr.Zeng
 * @date: 2022-07-12 17:41
 */
public class FakerHelper {

    private static final Faker FAKER = new Faker(new Locale("zh-CN"));

    static void example() {
        // 根据正则表达式生成字符串
        FAKER.regexify("re pattern");

        // 生成一个地址
        FAKER.address();

        // 随机生成一个推特头像地址
        FAKER.avatar();
    }


}
