package pers.zengsx.toolkit.test.example.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pers.zengsx.toolkit.test.example.dao.IUserDAO;
import pers.zengsx.toolkit.test.example.dao.mappers.IUserMapper;
import pers.zengsx.toolkit.test.example.model.User;

/**
 * @class-name: UserDAOImpl
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:12
 */
@Repository
@AllArgsConstructor
public class UserDAOImpl implements IUserDAO {

    private final IUserMapper userMapper;

    @Override
    public User getById(long id) {
        return userMapper.selectById(id);
    }

}
