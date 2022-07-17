package pers.zengsx.toolkit.test.sdk.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @class-name: TestApplication
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-11 12:10
 */
@SpringBootApplication
@Slf4j
public class TestApplication implements ApplicationRunner {

    static {
        log.info("load TestApplication class.");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("load spring by TestApplication.");
    }

}
