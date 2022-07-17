package pers.zengsx.toolkit.test.sdk.plugin.auto_load_sql_file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import pers.zengsx.toolkit.test.sdk.utils.ApplicationContextUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @class-name: AutoLoadSqlFileCore
 * @description: 根据TestClass、Method的名称在resources/it-sql去寻找sql file并加载
 * @author: Mr.Zeng
 * @date: 2022-07-12 12:21
 */
@Slf4j
public class AutoLoadSqlFileCore {

    private static final String SQL_DIR = Optional.ofNullable(System.getenv("AUTO_LOAD_SQL_FILE_DIR_NAME")).orElse("it-sql");

    public static boolean load(String className, String methodName) {
        String finalFileName = generateFileName(className, methodName);
        String sqlScript = getSqlFile(finalFileName);
        if (ObjectUtils.isEmpty(sqlScript)) {
            return false;
        }
        try (Connection connection = ApplicationContextUtils.getBean(DataSource.class).getConnection()) {
            connection.createStatement().execute(sqlScript);
            log.info("load sql file.  file name: {}", finalFileName);
            return true;
        } catch (SQLException ex) {
            log.error("load sql file error.  file name: {}", finalFileName, ex);
        }
        return false;
    }

    private static String getSqlFile(String fileName) {
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName);
            return IOUtils.toString(new FileInputStream(file), StandardCharsets.UTF_8);
        } catch (FileNotFoundException ignore) {
        } catch (Exception ex) {
            log.error("get sql file error. file-name: {}", fileName, ex);
        }
        return null;
    }

    private static String generateFileName(String m, String n) {
        if (ObjectUtils.isEmpty(n)) {
            return SQL_DIR + File.separator + m.replaceAll("\\.", "_") + ".sql";
        }
        return SQL_DIR + File.separator + m.replaceAll("\\.", "_") + "_" + n + ".sql";
    }

}
