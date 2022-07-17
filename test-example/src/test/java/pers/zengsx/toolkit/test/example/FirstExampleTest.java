package pers.zengsx.toolkit.test.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import pers.zengsx.toolkit.test.example.custom.ExampleLiteTest;
import pers.zengsx.toolkit.test.example.custom.Plugins;
import pers.zengsx.toolkit.test.example.dao.IUserDAO;
import pers.zengsx.toolkit.test.example.dao.impl.UserDAOImpl;
import pers.zengsx.toolkit.test.example.dao.mappers.IUserMapper;
import pers.zengsx.toolkit.test.example.model.User;
import pers.zengsx.toolkit.test.sdk.plugin.truncate_table.TruncateTable;
import pers.zengsx.toolkit.test.sdk.registrar.mybatis.LoadMybatisMapper;
import pers.zengsx.toolkit.test.sdk.utils.AssertHelper;

/**
 * @class-name: FirstExample
 * @description:
 * @author: Mr.Zeng
 * @date: 2022-07-08 11:48
 */
@ExampleLiteTest(Plugins.DAO)
@Import(UserDAOImpl.class)
@LoadMybatisMapper(classes = IUserMapper.class)
public class FirstExampleTest {

    @Autowired
    IUserDAO userDAO;

    @Test
    @TruncateTable(mpEntityClasses = User.class)
    void test() {
        AssertHelper.assertEqualsByJson("{\"id\":1,\"name\":\"mr.zeng\",\"nickname\":\"seven\"}", userDAO.getById(1));
        AssertHelper.assertEqualsByJson(null, userDAO.getById(2));
    }


}
