package com.my.mall.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.my.mall.pojo.Cart;
import com.my.mall.pojo.User;
import com.my.mall.service.DubboCartService;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    private static final String USERNAME = "JT_USER";
    
    @Reference(check=false)
    private DubboCartService cartService;
    
    //http://www.jt.com/order/create.html  
    @RequestMapping("/create")
    public String create(HttpServletRequest request,Model model) {
        //动态获取用户的购物车记录  ${carts}
        User user = (User) request.getAttribute(USERNAME);
        Long userId = user.getId();
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        request.setAttribute("carts", cartList);
        return "order-cart";
    }
}