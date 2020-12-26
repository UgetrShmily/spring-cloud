package com.my.mall.service;

import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;
import com.my.mall.vo.EasyUiTableVo;
import com.my.mall.vo.SysResult;

public interface ItemService {
	public EasyUiTableVo<Item> queryItem(Integer page,Integer rows);
	public void saveItem(Item item,ItemDesc itemDesc);
	public void updateItem(Item item,ItemDesc itemDesc);
	public void updateStatus(Long[] ids, Integer status);
	public void deleteItems(Long[] ids);
	public ItemDesc findItemDescById(Long itemId);
	public Item findItemById(Long id);
}
