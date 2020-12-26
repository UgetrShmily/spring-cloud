package com.my.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.mall.mapper.ItemDescMapper;
import com.my.mall.mapper.ItemMapper;
import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;

@RestController
@RequestMapping("/web/item")
public class WebItemController {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@PostMapping("/findItemById")
	public Item findItemById(Long id) {
		return itemMapper.selectById(id);
	}
	@GetMapping("/findItemDescById")
    public ItemDesc findItemDescById(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }
}
