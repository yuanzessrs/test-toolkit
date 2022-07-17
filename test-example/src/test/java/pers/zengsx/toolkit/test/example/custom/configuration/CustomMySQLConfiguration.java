package pers.zengsx.toolkit.test.example.custom.configuration;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import pers.zengsx.toolkit.test.sdk.enabler.EnableMySQLSupport;

/**
 * @class-name: CustomMySQLConfiguration
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:27
 */
@Configuration
@EnableMySQLSupport
@ImportAutoConfiguration(MybatisPlusAutoConfiguration.class)
public class CustomMySQLConfiguration {


}
