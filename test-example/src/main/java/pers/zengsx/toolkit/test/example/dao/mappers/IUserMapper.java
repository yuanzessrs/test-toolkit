package pers.zengsx.toolkit.test.example.dao.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.zengsx.toolkit.test.example.model.User;

/**
 * @interface-name: IUserMapper
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:11
 */
@Mapper
public interface IUserMapper extends BaseMapper<User> {


}
