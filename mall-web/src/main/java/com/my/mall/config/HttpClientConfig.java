package com.my.mall.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/httpclient.proptertis")
public class HttpClientConfig {
	@Value("${http.maxTotal}")
	private Integer maxTotal;                       //最大连接数​

	@Value("${http.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute;             //最大并发链接数​

	@Value("${http.connectTimeout}")
	private Integer connectTimeout;                 //创建链接的最大时间​

	@Value("${http.connectionRequestTimeout}") 
	private Integer connectionRequestTimeout;       //链接获取超时时间​

	@Value("${http.socketTimeout}")
	private Integer socketTimeout;                  //数据传输最长时间​

	@Value("${http.staleConnectionCheckEnabled}")
	private boolean staleConnectionCheckEnabled;    //提交时检查链接是否可用

	/**
	 * 连接池描述
	 * @return
	 */
	@Bean(name = "httpClientConnectionManager")
	public PoolingHttpClientConnectionManager PoolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
		manager.setMaxTotal(maxTotal); //最大连接数
		manager.setDefaultMaxPerRoute(defaultMaxPerRoute); //并发连接数
		return manager;
	}

	/**
	 * 实例化构造器
	 * @param manager
	 * @return
	 */
	@Bean("httpClientBuilder")
	public HttpClientBuilder httpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager manager) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		return httpClientBuilder.setConnectionManager(manager);
	}
	
	/**
	 * CloseableHttpClient连接池构建
	 * @param httpClientBuilder
	 * @return
	 */
	@Bean
	public CloseableHttpClient closeableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
		return httpClientBuilder.build();
	}

	/**
	 * 配置请求连接超时，传输超时，提交验证连接是否可用
	 * @return
	 */
	@Bean
	public RequestConfig requestConfig() {
		RequestConfig.Builder builder=RequestConfig.custom();
		builder.setConnectTimeout(connectTimeout)
			   .setSocketTimeout(socketTimeout)
			   .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
		return builder.build();
	}
}
