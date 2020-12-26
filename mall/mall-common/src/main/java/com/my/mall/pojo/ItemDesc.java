package com.my.mall.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("tb_item_desc")
@Data
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemDesc extends BasePojo{
	@TableId
	private Long itemId;	//id信息应该一致
	private String itemDesc;
}

