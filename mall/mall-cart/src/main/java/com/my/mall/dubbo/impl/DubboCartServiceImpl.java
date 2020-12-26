package com.my.mall.dubbo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.my.mall.dao.CartMapper;
import com.my.mall.pojo.Cart;
import com.my.mall.service.DubboCartService;
@Service
public class DubboCartServiceImpl implements DubboCartService{
	@Autowired
	private CartMapper cartMapper;
	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>().eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
	}
	@Override
	@Transactional
	public void updateCartNum(Cart cart) {
		Cart cartTemp = new Cart();
        cartTemp.setNum(cart.getNum())
                .setUpdated(new Date());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", cart.getUserId())
                     .eq("item_id", cart.getItemId());
        cartMapper.update(cartTemp, updateWrapper);	
	}
	@Override
	public void deleteCart(Cart cart) {
		QueryWrapper<Cart> wrapper=new QueryWrapper<>(cart);
		cartMapper.delete(wrapper);
	}
	public void saveCart(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.eq("user_id", cart.getUserId())
                    .eq("item_id", cart.getItemId());
        Cart cartDB = cartMapper.selectOne(queryWrapper);
        //判断是否有值
        if(cartDB == null) {
            cart.setCreated(new Date())
                .setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        }else {
            //更新操作
            int num = cart.getNum() + cartDB.getNum();
            //cartDB.setNum(num).setUpdated(new Date());
            //UPDATE tb_cart SET user_id=?, item_id=?, item_title=?, item_image=?, item_price=?, num=?, created=?, updated=? WHERE id=?
            //UPDATE tb_cart SET num=?,updated=? WHERE id=?
            Cart cartTemp = new Cart();
            cartTemp.setNum(num).setUpdated(new Date());
            UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<Cart>();
            updateWrapper.eq("id", cartDB.getId());
            cartMapper.update(cartTemp, updateWrapper);
        }
    }
}
