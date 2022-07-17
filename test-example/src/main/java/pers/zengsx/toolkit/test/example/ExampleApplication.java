package pers.zengsx.toolkit.test.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pers.zengsx.toolkit.test.sdk.configurations.EmbeddedMySQLConfiguration;

/**
 * @class-name: ExampleApplication
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:15
 */
@SpringBootApplication
@Import(EmbeddedMySQLConfiguration.class)
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
