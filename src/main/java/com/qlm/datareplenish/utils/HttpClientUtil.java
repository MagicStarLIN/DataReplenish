package com.qlm.datareplenish.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HttpClientUtil
 * @Description: http工具类
 * @author xys
 * @date 2019年6月25日
 * @version 1.0
 */
public class HttpClientUtil {

	/**
	 * @Title: getRequest
	 * @Description: get请求
	 * @return String 返回类型
	 * @date 2018年12月28日 下午2:53:43
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getRequest(String url, Map<String, String> params) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (params != null) {
				for (String key : params.keySet()) {
					builder.addParameter(key, params.get(key));
				}
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(300*1000).setConnectionRequestTimeout(60*1000)
					.setSocketTimeout(300*1000).build();
			httpGet.setConfig(requestConfig);
			httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * @Title: postRequest
	 * @Description: post请求
	 * @return String 返回类型
	 * @date 2019年6月25日 下午2:54:13
	 * @param url
	 * @param params
	 * @return
	 */
	public static String postRequest(String url, Map<String, Object> params) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(300*1000).setConnectionRequestTimeout(60*1000)
					.setSocketTimeout(300*1000).build();
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");

			// 创建参数列表
			if (params != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					paramList.add(new BasicNameValuePair(key, params.get(key).toString()));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * @Title: postRequestJson
	 * @Description: postJson请求
	 * @return String 返回类型
	 * @date 2018年12月28日 下午2:55:15
	 * @param url
	 * @param json
	 * @return
	 */
	public static String postRequestJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(300*1000).setConnectionRequestTimeout(60*1000)
					.setSocketTimeout(300*1000).build();
			httpPost.setConfig(requestConfig);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String requestOnce(final String url, String data,String rewardMchid) throws Exception {
		BasicHttpClientConnectionManager connManager;
		connManager = new BasicHttpClientConnectionManager(
				RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.getSocketFactory())
						.register("https", SSLConnectionSocketFactory.getSocketFactory())
						.build(),
				null,
				null,
				null
		);

		HttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(connManager)
				.build();

		HttpPost httpPost = new HttpPost(url);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000)
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(10000).build();

		httpPost.setConfig(requestConfig);

		StringEntity postEntity = new StringEntity(data, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + rewardMchid);
		httpPost.setEntity(postEntity);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		String reusltObj = EntityUtils.toString(httpEntity, "UTF-8");
		//logger.info("请求结果:" + reusltObj);
		return reusltObj;

	}

}
