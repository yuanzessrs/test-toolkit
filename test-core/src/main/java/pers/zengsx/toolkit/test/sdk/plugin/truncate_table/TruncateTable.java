package pers.zengsx.toolkit.test.sdk.plugin.truncate_table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: TruncateTable
 * @description: 表-DELETE删除
 * @author: Mr.Zeng
 * @date: 2022/7/10 23:15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TruncateTable {

    /**
     * 当存在mybatis-plus的实体类配置时，会去根据 @TableName注解生成 DELETE FROM $TABLE_NAME 的 sql
     */
    Class<?>[] mpEntityClasses() default {};

}
