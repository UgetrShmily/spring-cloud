package com.my.mall.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.mall.pojo.User;
@Mapper
public interface UserMapper extends BaseMapper<User>{

}
