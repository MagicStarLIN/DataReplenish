package com.qlm.datareplenish.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
*/
public class HttpClientUtils {

	private static CloseableHttpClient client;
	private static PoolingHttpClientConnectionManager cm = null;
	private final static Object syncLock = new Object();
	
	static {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(20);
        Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("HttpClient Stop");
				try {
					if(cm!=null) {
						cm.shutdown();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static CloseableHttpClient getHttpClient() {
		if(client == null){
            synchronized (syncLock){
                if(client == null){
                	client = HttpClients.custom()
                		.setConnectionManager(cm)
                		.build();
                	System.out.println("HttpClient Create!");
                }
            }
        }
        return client;
	}
	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static String getHttpEntiy(CloseableHttpResponse response) throws IOException {
		String responseResult = null;
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			responseResult = EntityUtils.toString(entity);

		}
		return responseResult;
	}
}
