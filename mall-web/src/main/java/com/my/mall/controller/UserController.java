package com.my.mall.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;
import com.my.mall.pojo.User;
import com.my.mall.service.DubboUserService;
import com.my.mall.vo.SysResult;

import redis.clients.jedis.JedisCluster;
@Controller
@RequestMapping("/user")
public class UserController {
	//容器启动后可能还没有服务提供者被注册
	@Reference(timeout = 60000)
	private DubboUserService dubboUserService;
	
	@ResponseBody
	@RequestMapping("/doRegister")
    public SysResult doRegister(User user) {
		System.out.println("============="+dubboUserService);
        dubboUserService.doRegister(user);
        return SysResult.success();
    }
	@ResponseBody
	@RequestMapping("/html")
	public String html() {
		return dubboUserService.getString();
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
    public SysResult doLogin(User user,HttpServletResponse response) {
        
        //1.调用远程jt-sso服务器
        String uuid = dubboUserService.doLogin(user);
        
        //2.检验uuid是否为null
        if(StringUtils.isEmpty(uuid)) {
            //说明后台服务器校验不通过.
            return SysResult.fail();
        }
        
        //3.用户登陆正常,之后需要将用户数据写入cookie中
        Cookie cookie = new Cookie("JT_TICKET", uuid);
        cookie.setMaxAge(7*24*60*60);   //7天超时
        cookie.setDomain("mall.com");     //设定cookie数据共享
        //www.jd.com/find 可以获取
        //www.jd.com/aaa/find   可以获取的!!
        cookie.setPath("/");            //当前cookie在根目录中有效
        response.addCookie(cookie);
        
        //4.返回正确的数据
        return SysResult.success();
    }
	@Autowired
	private JedisCluster jedisCluster;
	

	@RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                if("JT_TICKET".equals(cookie.getName())) {
                    String ticket = cookie.getValue();
                    //删除redis数据
                    jedisCluster.del(ticket);
                    System.out.println("============删除单点登录信息===========");
                    //删除cookie时需要重新为cookie赋值,否则删除失效
                    cookie.setMaxAge(0);         //立即删除
                    cookie.setDomain("jt.com");  //设定cookie数据共享
                    cookie.setPath("/");         //当前cookie在根目录中有效
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/";
    }
}
