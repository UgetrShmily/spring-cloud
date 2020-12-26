package com.my.mall.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class httpClientService {
	@Autowired
	private CloseableHttpClient httpClient;
	@Autowired
	private RequestConfig requestConfig;

	public String doGet(String url,Map<String, String> params,String charset) {
		if(params!=null) {
			url+="?";
			Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, String> entity=iterator.next();
				url+=entity.getKey()+"="+entity.getValue()+"&";
				System.out.println(entity.getKey()+entity.getValue());
			}
			url=url.substring(0, url.length()-1);
			System.out.println(url);
		}
		HttpGet httpGet = new HttpGet(url);
		//设置请求参数
		httpGet.setConfig(requestConfig);
		String result=null;
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==200) {
				result= EntityUtils.toString(response.getEntity(),charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("数据获取失败");
		}
		return result;
	}

	public String doPost(String url,Map<String, String> params,String charSet) {
		HttpPost httpPost=new HttpPost(url);
		httpPost.setConfig(requestConfig);
		String result=null;
		if(!params.isEmpty()) {
			ArrayList<NameValuePair> arrayList = new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entity:params.entrySet()) {
				arrayList.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
			}
			try {
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList);
				httpPost.setEntity(urlEncodedFormEntity);//设置form表单
				CloseableHttpResponse response = httpClient.execute(httpPost);
				if(response.getStatusLine().getStatusCode()==200)
					result=EntityUtils.toString(response.getEntity(),charSet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
