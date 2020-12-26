package com.my.mall;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.mall.service.httpClientService;

import redis.clients.jedis.JedisCluster;
@SpringBootTest
public class SpringTerst {
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		String url="https://www.baidu.com";
		HttpGet httpGet = new HttpGet(url);
		HttpPost httpPost=new HttpPost(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode==200) {
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity,"utf-8"));
		}
		
	}
	@Autowired
	CloseableHttpClient httpClient;
	@Test
	public void testPool() throws ClientProtocolException, IOException {
		HttpGet get=new HttpGet("https://www.baidu.com");
		HttpResponse rep=httpClient.execute(get);
		if(rep.getStatusLine().getStatusCode()==200) {
			HttpEntity entity=rep.getEntity();
			System.out.println(EntityUtils.toString(entity));
		}
	}
	@Autowired
	private httpClientService httpcli;
	@Test
	public void testPost() {
		Map<String, String> params=new HashMap<>();
		params.put("id", "12");
		params.put("name", "dnf");
		params.put("like", "篮球");
		params.put("like", "足球");
		for(Map.Entry<String, String> e:params.entrySet()) {
			System.out.println("============");
			System.out.println(e.getKey()+"="+e.getValue());
		}
		System.out.println(httpcli.doPost("http://www.baidu.com", params, "utf-8"));
	}
	@Autowired
	private JedisCluster jedisCluster;
	@Test
	public void jedis() {
		System.out.println(jedisCluster);
	}
}
