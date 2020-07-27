package com.jfast.core.http.config;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpClientConnectionEvictor extends Thread {
 
@Autowired
private HttpClientConnectionManager connMgr;
 
private volatile boolean shutdown;
 
	public HttpClientConnectionEvictor() {
		super();
		super.start();
	}
 
	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000);
					// 关闭失效的连接
					connMgr.closeExpiredConnections();
				}
			}
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void shutdown() {
		shutdown = true;
		synchronized (this) {
		notifyAll();
		}
	}
	
}