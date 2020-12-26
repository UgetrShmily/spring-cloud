package com.my.mall.aspect;

import java.util.Arrays;
import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.my.mall.annotation.RequiredCache;
import com.my.mall.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Aspect
@Component
public class CacheAspect {
	@Autowired
	private JedisCluster jedis;
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.my.mall.annotation.RequiredCache)")
	public <T> T around(ProceedingJoinPoint pj) {
		//获取目标方法
		MethodSignature signature = (MethodSignature)pj.getSignature();
		//获取方法上的注解
		RequiredCache requiredCache=signature.getMethod().getAnnotation(RequiredCache.class);
		//获取key
		String key=requiredCache.key();
		if(StringUtils.isEmpty(key)) {
			key=pj.getSignature().toString()+Arrays.toString(pj.getArgs()).hashCode();
		}
		System.out.println("key="+key);
		String value=jedis.get(key);

		T t=null;

		//缓存无数据
		try {
			if(StringUtils.isEmpty(value)) {
				t=(T) pj.proceed();
				value=ObjectMapperUtil.toJson(t);
				//超时时间
				int minSecondes=requiredCache.minSecondes();
				int maxSecondes=requiredCache.maxSecondes();
				if(minSecondes==-1) {
					//不需要超时时间
					jedis.set(key, value);
				}
				else{
					int secondes=minSecondes+new Random().nextInt(maxSecondes-minSecondes+1);
					jedis.setex(key, secondes, value);
					System.out.println("key超时设置为："+jedis.ttl(key)+"s");
				}
				System.out.println("==================数据来自于数据库===================");
			}
			else {
				t=(T) ObjectMapperUtil.toObject(value, signature.getReturnType());
				System.out.println("key超时时间："+jedis.ttl(key)+"s");
				System.out.println("==================数据来自于redis===================");
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("数据查询失败");
		}finally {
			//释放连接,归还连接池(集群连接关闭)
			//jedis.close();
		}
		return t;
	}
}
