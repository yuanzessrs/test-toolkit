package pers.zengsx.toolkit.test.example.dao;

import pers.zengsx.toolkit.test.example.model.User;

/**
 * @interface-name: IUserDAO
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:11
 */
public interface IUserDAO {

    User getById(long id);

}
