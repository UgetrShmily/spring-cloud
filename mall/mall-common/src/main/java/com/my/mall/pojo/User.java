package com.my.mall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@TableName("tb_user")
public class User extends BasePojo {
	@TableId(type=IdType.AUTO)
    private Long id;
    private String username;    //设定用户名
    private String password;    //设定密码    md5
    private String phone;       //设定电话
    private String email;       //设定email地址
}
