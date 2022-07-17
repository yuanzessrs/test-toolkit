package pers.zengsx.toolkit.test.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @class-name: User
 * @description:
 * @author: Mr.Zeng
 * @date: 2022/7/17 20:09
 */
@TableName("users")
@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("nickname")
    private String nickname;

}
