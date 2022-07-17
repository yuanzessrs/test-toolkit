package pers.zengsx.toolkit.test.sdk.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;

/**
 * @class-name: Json
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-12 14:31
 */
@Slf4j
public class Json {

    private static final ObjectMapper OM = new ObjectMapper();

    static {
        OM.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OM.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private Json() {

    }

    @SneakyThrows
    public static String toString(Object object) {
        if (object == null) {
            return null;
        }
        return OM.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T parse(String jsonString, Class<T> clazz) {
        if (ObjectUtils.isEmpty(jsonString)) {
            return null;
        }
        return OM.readValue(jsonString, clazz);
    }

}
