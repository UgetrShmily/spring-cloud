package com.my.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;
import com.my.mall.service.ItemService;
@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/{itemId}")
    public String findItemById(@PathVariable Long itemId,Model model) {
        System.out.println("========="+itemId+"=========");
        Item item = itemService.findItemById(itemId);
        model.addAttribute("item", item);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        model.addAttribute("itemDesc", itemDesc);
        return "item";  //商品页面逻辑名称
    }
}
