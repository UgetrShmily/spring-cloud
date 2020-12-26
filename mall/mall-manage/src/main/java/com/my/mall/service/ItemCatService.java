package com.my.mall.service;

import java.util.List;

import com.my.mall.vo.EasyUiTreeVo;

public interface ItemCatService {
	public String findItemCatService(Long itemCatId);
	public List<EasyUiTreeVo> findItemCatListByParentId(Long parentId);
}
