package com.my.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.my.mall.pojo.Cart;
import com.my.mall.pojo.User;
import com.my.mall.service.DubboCartService;
import com.my.mall.vo.SysResult;


@Controller
@RequestMapping("/cart")
public class CartController {
	@Reference(timeout = 60000)
	private DubboCartService cartService;
	
	@RequestMapping("/show")
    public String show(Model model,HttpServletRequest request) {
		System.out.println("+++++++++++"+cartService+"+++++++++");
		User user=(User)request.getAttribute("JT_USER");
        Long userId = user.getId();
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("cartList", cartList);
        return "cart";
    }
	@RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult updateCartNum(Cart cart) {
        
        Long userId = 7L;
        cart.setUserId(userId);
        cartService.updateCartNum(cart);
        return SysResult.success();
    }
	@RequestMapping("/delete/{itemId}")
    public String  deleteCart(Cart cart) {
        
        Long userId = 7L;
        cart.setUserId(userId);
        cartService.deleteCart(cart);
        return "redirect:/cart/show.html";  //重定向到购物车列表页面
    }
	@RequestMapping("/add/{itemId}")
    public String saveCart(Cart cart) {
        
        Long userId = 7L;
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html";  //重定向到购物车列表页面
    }
}
