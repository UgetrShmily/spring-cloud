package com.my.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;
import com.my.mall.service.ItemService;
import com.my.mall.vo.EasyUiTableVo;
import com.my.mall.vo.SysResult;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	public EasyUiTableVo<?> findItems(Integer page,Integer rows) {
		return itemService.queryItem(page, rows);
	}
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {

		try {
			itemService.saveItem(item,itemDesc); //100%
			return SysResult.success(); //统一异常处理
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {

		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("/instock")	
	public SysResult itemInstock(Long[] ids) {

		int status = 2;	//下架操作
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	@RequestMapping("/reshelf")	//状态改为1
	public SysResult itemReshelf(Long[] ids) {

		int status = 1;	//上架操作
		itemService.updateStatus(ids,status);
		return SysResult.success();
	}
	@RequestMapping("/delete")
	public SysResult itemDeletes(Long[] ids) {
		
		itemService.deleteItems(ids);
		return SysResult.success();
	}
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		return SysResult.success(itemDesc);
	}
	@RequestMapping("/query/item/{itemId}")
	public SysResult findItemById(@PathVariable Long itemId) {
		
		Item item = itemService.findItemById(itemId);
		return SysResult.success(item);
	}
}
