package com.my.mall.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.mall.pojo.Item;
import com.my.mall.pojo.ItemDesc;
import com.my.mall.util.ObjectMapperUtil;

@Service
public class ItemService {
	@Autowired
    private httpClientService httpClient;
	public Item findItemById(Long id) {
		String url = "http://manage.mall.com/web/item/findItemById";
        Map<String,String> params = new HashMap<String, String>();
        params.put("id", ""+id);
        String result=httpClient.doPost(url, params, "utf-8");
        System.out.println(result);
		return ObjectMapperUtil.toObject(result, Item.class);
	}
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.mall.com/web/item/findItemDescById";
        Map<String,String> params = new HashMap<String, String>();
        params.put("itemId", ""+itemId);
        String result=httpClient.doGet(url, params, "utf-8");
        System.out.println(result);
		return ObjectMapperUtil.toObject(result, ItemDesc.class);
	}
}
