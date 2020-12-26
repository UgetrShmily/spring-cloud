package com.my.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.mall.service.ItemCatService;
import com.my.mall.vo.EasyUiTreeVo;
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@PostMapping("/queryItemName")
	public String queryItemName(Long itemCatId) {
		System.out.println(itemCatId);
		return itemCatService.findItemCatService(itemCatId);
	}
	@RequestMapping("/list")
	public List<EasyUiTreeVo> findTreeByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		return itemCatService.findItemCatListByParentId(parentId);
	}

}
