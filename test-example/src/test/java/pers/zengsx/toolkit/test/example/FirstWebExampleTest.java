package pers.zengsx.toolkit.test.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import pers.zengsx.toolkit.test.example.controller.TestController;
import pers.zengsx.toolkit.test.example.custom.ExampleLiteWebTest;
import pers.zengsx.toolkit.test.example.dao.IUserDAO;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @class-name: FirstWebExample
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-08 12:18
 */
@ExampleLiteWebTest
@Import(TestController.class)
public class FirstWebExampleTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IUserDAO userDAO;

    @Test
    @SneakyThrows
    void test() {
        mockMvc.perform(get("/test/HelloWorld"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("HelloWorld")));

        assertThrows(NestedServletException.class, () -> mockMvc.perform(get("/test/throwError")));
    }

}
