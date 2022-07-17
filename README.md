### 介绍

在毕业后的第一家公司和第二家公司，基本上都在做代码迁移重构（切换语言）的工作。第一家是php转java，现在的公司是 clojure转java。因为重构是一个人能完成的工作，不需要和客户端对接，所以是能够全身心投入的一个过程。在熟悉既有的业务以及与外部交互的入参、出参后，就可以对任意逻辑进行迁移以及重新设计了。



对于重构，我很在意自己的代码质量。以及日常的改动影响了线上了业务，如果通过本地启动项目，或者在测试环境再去验证业务功能，这些步骤无疑是很浪费时间的。所以通过有意义的测试用例，每次合并代码前，进行自动化的测试就很放心。



以前写测试用例时，比较死板。总是看着代码逻辑去写测试用例，想着通过什么数据能够覆盖所有分支，导致一有改动就会去改测试用例。时间久了，就会觉得测试用例很浪费时间，写着没什么意思。但是自从接触了spock（BDD）后，也走出了通过数据来覆盖分支的误区。我们开发的业务，不是一成不变的，不可能每个方法都能100%覆盖并且永远不改动，只要能够通过测试用例覆盖大多数场景即可。



通过@SpringbootTest(classes=XxxApplication.class)的方式来进行测试，显得很笨重，会增加测试用例运行时间。通过对Spring Test的理解，我对日常用到的测试工具进行了精简和封装。

包括：

- 最小化的spring容器环境
- 自动根据测试方法加载sql
- 通过ORM实体类生成truncate sql的注解
- 按需加载myabtis-plus-mapper的注解
- 断言工具类的封装

### 测试支持

#### 相关文献阅读

[理解单元测试、TDD、BDD](https://zhuanlan.zhihu.com/p/91136759)

#### 介绍

测试环境支持了junit5、spock两种测试框架。基于springboot的集成测试，抽象了相关的注解和插件。不同于现在各服务的用法，虽然使用了@SpringBootTest，但是去掉了自动装配（不会扫描哪些AutoConfiguration），也不会扫描当前项目里面的所有bean。

bean的加载是依据最小化原则，需要测哪些bean就用哪些bean，不用的就不加载

##### @SpringBootTest-冷知识

使用@SpringBootTest时，每次都会启动完整的容器。当我们依赖的AutoConfiguration和项目里面的bean比较多时，启动一个容器的成本是很大的。SpringTest在这块做了优化，会对容器进行缓存，涉及的类包含DefaultCacheAwareContextLoaderDelegate和MergedContextConfiguration等，判断是否走缓存的依据在MergedContextConfiguration的hashcode里面，代码如下：

```java
/**
 * Generate a unique hash code for all properties of this
 * {@code MergedContextConfiguration} excluding the
 * {@linkplain #getTestClass() test class}.
 */
@Override
public int hashCode() {
   int result = Arrays.hashCode(this.locations);
   result = 31 * result + Arrays.hashCode(this.classes);
   result = 31 * result + this.contextInitializerClasses.hashCode();
   result = 31 * result + Arrays.hashCode(this.activeProfiles);
   result = 31 * result + Arrays.hashCode(this.propertySourceLocations);
   result = 31 * result + Arrays.hashCode(this.propertySourceProperties);
   result = 31 * result + this.contextCustomizers.hashCode();
   result = 31 * result + (this.parent != null ? this.parent.hashCode() : 0);
   result = 31 * result + nullSafeClassName(this.contextLoader).hashCode();
   return result;
}
```

如果Profile，@SpringBootTest上面的Application，context相关的初始化器、自定义的PropertySource等有变化，就会重新创建一个容器。

上面这些都是改动比较少，this.classes（容器中所有对象的class）是最容易变动的，在测试中我们难免会MOCK对象，一旦两个测试用例MOCK的对象不同或者一个有一个没有，那么他们的容器也是分别启动的

而且如果服用容器，那就代表 @MockBean @SpyBean的对象都是复用，不同的测试用例对mock对象的mock逻辑都会叠加，单个单个跑没问题，但是当执行所有case时，就会失败部分用例。

#### 注解

##### pers.zengsx.toolkit.test.sdk.base.LiteTest

定义了一个最小化的容器（所以这也是一个一次性容器，每个测试用例类都是单独的容器，保证了每个测试用例类都是独立环境），并且关闭了自动加载bean。已包括的能力如下：

**插件**（后面专门讲）：

1. 自动读取加载SqlFile
2. 根据实体生成Truncate Table SQL

使用的服务一般都有自定义的组件，比如DAO、CACHE、LOCK等。在test-example的项目，对LiteTest进行了再次封装了，增加了plugins支持。详情见 pers.zengsx.toolkit.test.example.custom.ExampleLiteTest

示例如下：

```java
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

```

上面的示例启用了DAO组件，sql初始化数据是通过AUTO_LOAD_SQL组件自动加载的，测试了mybatis的查询。

##### pers.zengsx.toolkit.test.sdk.base.LiteWebTest

在LiteTest的基础上增加了MockMvc的支持

pers.zengsx.toolkit.test.example.custom.ExampleLiteWebTest 在 LiteWebTest 的基础上增加了项目的自定义支持

示例如下：

```java
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
```



##### pers.zengsx.toolkit.test.sdk.registrar.mybatis.LoadMybatisMapper

如果要使用 IXXXMapper（mybatis-plus的mapper），不能直接通过 @Import(IXXXMapper.class)进行导入（即使加了@Mapper注解），这里做了特殊处理才能支持。



##### pers.zengsx.toolkit.test.sdk.plugin.truncate_table.TruncateTable

当测试用例上用了 @TruncateTable(mpEntityClasses=XxxAutogenMybatisPO.class), 会去读取XxxAutogenMybatisPO.class上面的@TableName注解，生成truncate table sql并执行

#### 插件

##### TruncateTable-ORM Entity Truncate

描述同上 @TruncateTable

##### AutoLoadSqlFile-自动加载SQL FIle(目前spock test case不支持)

在执行测试用例之前会根据当前的类名和方法名去 resources/it-sql下面去找 **类名_方法名.sql** 并加载到数据库中_

#### Spock

##### 介绍

spock编写测试用例在**语法**上比junit方便许多。除了个别注解有差异/语法差异，其他的都差不多

[spock入门](http://blog.hylwisdom.xyz/pages/c68875/)

[Spock官方文档（方便查阅和整体学习）](https://spockframework.org/spock/docs/2.1/all_in_one.html#_introduction)

下面仅举例两个，其他的自行探索

示例如下(**最喜欢的就是支持跨行字符串，可以直接复制json来比较object**)：

```groovy
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
```



