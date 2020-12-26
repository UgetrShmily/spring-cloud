package com.my.mall.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.my.mall.pojo.Cart;

public interface DubboCartService {

	List<Cart> findCartListByUserId(Long userId);
	@Transactional
	void updateCartNum(Cart cart);
	void deleteCart(Cart cart);
	void saveCart(Cart cart);

}
