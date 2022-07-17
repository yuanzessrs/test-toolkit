package pers.zengsx.toolkit.test.sdk.base;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import pers.zengsx.toolkit.test.sdk.base.ext.DisableAutoConfiguration;
import pers.zengsx.toolkit.test.sdk.base.ext.EnableAopAutoConfiguration;
import pers.zengsx.toolkit.test.sdk.enabler.EnablePlugins;

import java.lang.annotation.*;

/**
 * @annotation-name: BaseLiteTest
 * @description: -
 * todo:
 * 1. 代码异味扫描
 * 2. 测试覆盖率扫描
 * 3. sql数据 注解支持
 * <p>
 * Potentially helpful:
 * 1. Faker
 * 2. @MockBean @SpyBean
 * 3. @TestExecutionListeners
 * 4. ReflectionTestUtils  AopTestUtils
 * 5. MockHttpServletRequest MockHttpServletResponse
 * 6. @Tag  test-group
 * 7. TruncateTable  listener
 * 8. result judge utils
 * 9. Assertions 断言工具
 * 10. JdbcTestUtils
 * 11. Hamcrest matcher
 * 12. @Timeout
 * 13. JsonExpectationsHelper JsonPathExpectationsHelper
 * <p>
 * related documents:
 * 1. https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
 * @author: Mr.Zeng
 * @date: 2022/7/9 22:49
 */
@ActiveProfiles("it")
@SpringBootTest
// @Import(项目中的基础bean【每个case都会用到的公共bean】)
@EnablePlugins
@DisableAutoConfiguration
@EnableAopAutoConfiguration
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface BaseLiteTest {

    @AliasFor(annotation = SpringBootTest.class, attribute = "classes")
    Class<?>[] classes() default TestApplication.class;

}
