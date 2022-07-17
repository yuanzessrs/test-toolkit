package pers.zengsx.toolkit.test.sdk.enabler;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import pers.zengsx.toolkit.test.sdk.plugin.auto_load_sql_file.AutoLoadSqlFileTestExecutionListener;
import pers.zengsx.toolkit.test.sdk.plugin.truncate_table.TruncateTableTestExecutionListener;
import pers.zengsx.toolkit.test.sdk.utils.ApplicationContextUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @annotation-name: EnablePlugins
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/10 23:24
 */
@TestExecutionListeners(listeners = {
        TruncateTableTestExecutionListener.class,
        AutoLoadSqlFileTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@Import(ApplicationContextUtils.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePlugins {
}
