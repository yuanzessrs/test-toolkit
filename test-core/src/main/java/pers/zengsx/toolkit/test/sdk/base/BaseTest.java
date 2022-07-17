package pers.zengsx.toolkit.test.sdk.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @class-name: BaseTest
 * @description: goal: All test cases share context
 * @author: Mr.Zeng
 * @date: 2022/7/10 14:46
 */
@BaseLiteTest
@AutoConfigureMockMvc
public abstract class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

}
