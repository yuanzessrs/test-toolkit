package pers.zengsx.toolkit.test.sdk.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.util.Optional;

/**
 * @class-name: EmbeddedRedisServerConfiguration
 * @description: https://github.com/ozimov/embedded-redis
 * @author: Mr.Zeng
 * @date: 2021-12-09 16:50:26
 */
@Slf4j
@Configuration
public class EmbeddedRedisServerConfiguration {

    private static final int REDIS_PORT = Optional.ofNullable(System.getenv("TEST_REDIS_PORT")).map(Integer::parseInt).orElse(6479);

    private static final RedisServer REDIS_SERVER;

    static {
        REDIS_SERVER = RedisServer.builder().port(REDIS_PORT).setting("bind localhost").build();
        REDIS_SERVER.start();
        log.info("redis server start.");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (REDIS_SERVER.isActive()) {
                REDIS_SERVER.stop();
                log.info("redis server stop.");
            }
        }));
    }

}
