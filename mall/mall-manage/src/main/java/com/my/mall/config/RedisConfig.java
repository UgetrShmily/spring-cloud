package com.my.mall.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import lombok.Data;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Data
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	@Value("${redis.node}")
	private String redisNode;
	
	@Bean
	@Scope("prototype")
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes=new HashSet<>();
		String[] nodeArray=redisNode.split(",");
		for(String node:nodeArray) {
			String [] hostAndPort=node.split(":");
			System.out.println(hostAndPort[0]+":"+hostAndPort[1]);
			nodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
		}
		return new JedisCluster(nodes);
	}
}
