package pers.zengsx.toolkit.test.sdk.plugin.truncate_table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.extern.slf4j.Slf4j;
import pers.zengsx.toolkit.test.sdk.utils.ApplicationContextUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * @class-name: TruncateTableCore
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-12 14:22
 */
@Slf4j
public class TruncateTableCore {

    private static final String TRUNCATE_TABLE_SQL_TEMPLATE = "TRUNCATE TABLE  %s ;";

    public static void exec(TruncateTable truncateTable) {
        Optional.ofNullable(truncateTable).map(TruncateTable::mpEntityClasses).ifPresent(mpEntityClasses -> {
            for (Class<?> mpEntityClass : mpEntityClasses) {
                TableName tableName = mpEntityClass.getAnnotation(TableName.class);
                Objects.requireNonNull(tableName, String.format("The @TableName annotation was not found on the %s class", mpEntityClass.getName()));
                try (Connection connection = ApplicationContextUtils.getBean(DataSource.class).getConnection()) {
                    String sql = String.format(TRUNCATE_TABLE_SQL_TEMPLATE, tableName.value());
                    connection.createStatement().execute(sql);
                    log.info("exec sql : {}", sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
