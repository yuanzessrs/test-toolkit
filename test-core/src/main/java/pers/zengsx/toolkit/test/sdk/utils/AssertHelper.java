package pers.zengsx.toolkit.test.sdk.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.util.JsonExpectationsHelper;

/**
 * @class-name: AssertHelper
 * @description:
 * @author: Mr.Zeng
 * @date: 2021-12-10 16:05:26
 */
@Slf4j
public class AssertHelper {

    private static final JsonExpectationsHelper JSON_EXPECTATIONS_HELPER = new JsonExpectationsHelper();

    @SneakyThrows
    public static void assertEqualsByJson(String json, Object obj) {
        JSON_EXPECTATIONS_HELPER.assertJsonEqual(json, Json.toString(obj), true);
    }

    @SneakyThrows
    public static void assertEquals(Object obj1, Object obj2) {
        JSON_EXPECTATIONS_HELPER.assertJsonEqual(Json.toString(obj1), Json.toString(obj2));
    }

}
