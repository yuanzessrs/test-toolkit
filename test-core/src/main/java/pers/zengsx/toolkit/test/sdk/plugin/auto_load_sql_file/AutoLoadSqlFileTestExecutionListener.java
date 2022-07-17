package pers.zengsx.toolkit.test.sdk.plugin.auto_load_sql_file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import pers.zengsx.toolkit.test.sdk.plugin.truncate_table.TruncateTableTestExecutionListener;

/**
 * @class-name: AutoLoadSqlFileTestExecutionListener
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-11 16:11
 */
@Slf4j
public class AutoLoadSqlFileTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public int getOrder() {
        return new TruncateTableTestExecutionListener().getOrder() + 1;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        beforeTestExecution(testContext);
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        String simpleClassName = testContext.getTestClass().getSimpleName();
        String fullClassName = testContext.getTestClass().getName();
        String methodName = testContext.getTestMethod().getName();
        if (methodName.startsWith("$spock")) {
            // spock feature暂不支持 todo
            return;
        }
        if (!AutoLoadSqlFileCore.load(fullClassName, methodName)) {
            AutoLoadSqlFileCore.load(simpleClassName, methodName);
        }
    }

}
