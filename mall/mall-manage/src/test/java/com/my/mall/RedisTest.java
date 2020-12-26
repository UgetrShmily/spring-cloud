package com.my.mall;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.params.SetParams;

public class RedisTest {
	@Test
	public void testString() {
		String host="192.168.65.128";
		Integer port=6379;
		Jedis jedis = new Jedis(host, port);
		Transaction transaction = jedis.multi();
		jedis.set("count", "0",new SetParams().nx().ex(10));
		if(jedis.exists("t")) {
			jedis.set("t", "0");
		}
		transaction.exec();
		System.out.println(jedis.get("count"));
		System.out.println(jedis.ttl("count"));
	}
	@Test
	public void testShards() {
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		for(int i=0;i<3;i++) {
			shards.add(new JedisShardInfo("192.168.65.128", 6379+i));
		}
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		shardedJedis.set("123", "123");
		System.out.println(shardedJedis.get("123"));
	}
	@Test
	public void test02() {
		int a=10,b=5,i=10;
		while(i--!=0) {
			System.out.println((a-b)*Math.random()+b);
		}
	}
}
