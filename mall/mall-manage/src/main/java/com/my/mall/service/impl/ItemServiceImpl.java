package com.my.mall.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.mall.mapper.ItemDescMapper;
import com.my.mall.mapper.ItemMapper;
import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;
import com.my.mall.service.ItemService;
import com.my.mall.vo.EasyUiTableVo;
import com.my.mall.vo.SysResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Override
	public EasyUiTableVo<Item> queryItem(Integer page,Integer rows) {
		IPage<Item> ipage=new Page<>(page, rows);
		QueryWrapper<Item> queryWrapper=new QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		//分页查询后包装
		IPage<Item> selectPage = itemMapper.selectPage(ipage, queryWrapper);

		Integer total=(int) selectPage.getTotal();
		List<Item> items=selectPage.getRecords();
		return new EasyUiTableVo<Item>(total,items);
	}
	@Override
	@Transactional
	public void saveItem(Item item,ItemDesc itemDesc) {

		item.setStatus(1)  //上架状态
		.setCreated(new Date())
		.setUpdated(item.getCreated()); //时间相同
		itemMapper.insert(item);	//利用mp直接入库
		
		itemDesc.setItemId(item.getId())
				.setCreated(item.getCreated())
				.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void updateItem(Item item,ItemDesc itemDesc) {
		
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(new Date());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	public void updateStatus(Long[] ids, Integer status) {
		Item item = new Item();
		item.setStatus(status)
		.setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<Item>();
		//将数组转化为List集合
		List<Long> idList = Arrays.asList(ids);
		updateWrapper.in("id", idList);
		itemMapper.update(item, updateWrapper);
	}
	@Override
	@Transactional
	public void deleteItems(Long[] ids) {
		//1.利用MP方式实现数据传递
		//List<Long> idList = Arrays.asList(ids);
		//itemMapper.deleteBatchIds(idList);
		//2.利用xml配置文件,实现数据的删除. 切记不用与MP方法重名
		itemMapper.deleteItems(ids);
		itemDescMapper.deleteBatchIds(Arrays.asList(ids));
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectById(itemId);
	}
	@Override
	public Item findItemById(Long id) {
		return itemMapper.selectById(id);
	}



}
