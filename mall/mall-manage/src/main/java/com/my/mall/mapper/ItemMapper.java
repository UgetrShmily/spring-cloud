package com.my.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.mall.pojo.Item;

public interface ItemMapper extends BaseMapper<Item>{

	void deleteItems(Long[] ids);
	
}
