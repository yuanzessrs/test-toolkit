package pers.zengsx.toolkit.test.sdk.configurations;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * @class-name: EmbeddedMySQLConfiguration
 * @description: https://github.com/vorburger/MariaDB4j
 * @author: Mr.Zeng
 * @date: 2022-07-08 11:35
 */
@Configuration
@Slf4j
public class EmbeddedMySQLConfiguration {

    private static final int DB_PORT = Optional.ofNullable(System.getenv("TEST_MYSQL_PORT")).map(Integer::parseInt).orElse(3344);

    private static final DB INSTANCE;

    static {
        try {
            INSTANCE = DB.newEmbeddedDB(DB_PORT);
        } catch (ManagedProcessException e) {
            throw new RuntimeException(e);
        }
        try {
            INSTANCE.start();
            INSTANCE.createDB("test");
            INSTANCE.source("schema.sql");
            log.info("mariaDB start succeed");
        } catch (ManagedProcessException e) {
            throw new RuntimeException(e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                INSTANCE.stop();
                log.info("mariaDB stop succeed");
            } catch (ManagedProcessException e) {
                log.error("stop MariaDB fail.", e);
            }
        }));
    }

}
