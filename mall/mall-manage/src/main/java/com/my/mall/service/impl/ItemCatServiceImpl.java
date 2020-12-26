package com.my.mall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.mall.annotation.RequiredCache;
import com.my.mall.mapper.ItemCatMapper;
import com.my.mall.pojo.ItemCat;
import com.my.mall.service.ItemCatService;
import com.my.mall.vo.EasyUiTreeVo;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public String findItemCatService(Long itemCatId) {

		//查询商品分类对象
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		//获取商品分类名称
		return itemCat.getName();
	}
	@Override
	@RequiredCache(minSecondes= 60,maxSecondes = 3600)//[60,3600]
	public List<EasyUiTreeVo> findItemCatListByParentId(Long parentId) {
		
		//1.根据parentID查询数据库记录.
		List<ItemCat> itemCatList = findItemCatList(parentId);

		//2.itemCatList~~~~List<EasyUITree>
		List<EasyUiTreeVo> treeList = new ArrayList<>(itemCatList.size());

		for (ItemCat itemCat : itemCatList) {
			Long id = itemCat.getId();
			String text = itemCat.getName();
			//如果是父级 默认closed,否则开启open
			String state = itemCat.getIsParent()?"closed":"open";
			EasyUiTreeVo uiTree = new EasyUiTreeVo(id, text, state);
			treeList.add(uiTree);
		}
		return treeList;
	}

	//根据parentId查询分类信息
	private List<ItemCat> findItemCatList(Long parentId) {

		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}
	
}
