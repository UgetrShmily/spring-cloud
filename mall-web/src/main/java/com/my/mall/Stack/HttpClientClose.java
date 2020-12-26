package com.my.mall.Stack;

import javax.annotation.PreDestroy;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class HttpClientClose extends Thread{
	@Autowired
	private PoolingHttpClientConnectionManager manage;
	private volatile boolean shutdown;//开关，原子操作
	@Override
	public void run() {
		try {
			//没有关闭,线程关闭连接
			while(!shutdown) {
				synchronized (this) {
					wait(5000);	//释放锁并等待5s
					PoolStats totalStats = manage.getTotalStats();
					int av=totalStats.getAvailable();//可用线程数
					int pend=totalStats.getPending();//阻塞线程
					int lea=totalStats.getLeased();//正在使用线程
					int max=totalStats.getMax();//
					//System.out.println("可用线程数:"+av+"阻塞线程数:"+pend+"正在使用线程:"+lea+"max:"+max);
					manage.closeExpiredConnections();//关闭失效的连接
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("关闭失败");
		}
	}
	public HttpClientClose() {
		this.start();
	}
	@PreDestroy
	public void shutdown() {
		shutdown=true;
		synchronized (this) {
			notifyAll();	//唤醒该对象上的全部等待线程执行关闭
		}
	}
}
