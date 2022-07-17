package pers.zengsx.toolkit.test.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import pers.zengsx.toolkit.test.example.custom.ExampleLiteTest
import pers.zengsx.toolkit.test.example.custom.Plugins
import pers.zengsx.toolkit.test.example.dao.IUserDAO
import pers.zengsx.toolkit.test.example.dao.impl.UserDAOImpl
import pers.zengsx.toolkit.test.example.dao.mappers.IUserMapper
import pers.zengsx.toolkit.test.example.model.User
import pers.zengsx.toolkit.test.sdk.plugin.truncate_table.TruncateTable
import pers.zengsx.toolkit.test.sdk.registrar.mybatis.LoadMybatisMapper
import pers.zengsx.toolkit.test.sdk.utils.AssertHelper
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@ExampleLiteTest(Plugins.DAO)
@Import(UserDAOImpl.class)
@LoadMybatisMapper(classes = IUserMapper.class)
class FirstExampleGTest extends Specification {

    @Autowired
    IUserDAO userDAO

    @Sql(scripts = "classpath:it-sql/FirstExampleTest_test.sql")
    @TruncateTable(mpEntityClasses = User.class)
    def "test"() {
        expect:
        AssertHelper.assertEqualsByJson('''{
        "id":1,
        "name":"mr.zeng",
        "nickname":"seven"
        }''', userDAO.getById(1));


        AssertHelper.assertEqualsByJson(null, userDAO.getById(2));
    }

}
