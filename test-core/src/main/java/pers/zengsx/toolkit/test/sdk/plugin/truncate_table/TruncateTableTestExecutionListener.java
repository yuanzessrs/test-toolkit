package pers.zengsx.toolkit.test.sdk.plugin.truncate_table;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @class-name: TruncateTableTestExecutionListener
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/10 23:21
 */
@Slf4j
public class TruncateTableTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public int getOrder() {
        return new SqlScriptsTestExecutionListener().getOrder() - 1;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        beforeTestExecution(testContext);
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        TruncateTableCore.exec(testContext.getTestMethod().getAnnotation(TruncateTable.class));
    }

}
