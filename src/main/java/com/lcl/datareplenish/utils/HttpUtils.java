package com.lcl.datareplenish.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

//import net.sf.json.JSON;
//import net.sf.json.xml.XMLSerializer;

/*import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;*/

/****************************************************************************
 * @Author: linfenliang
 * @Description: Http请求工具类
 * @Version: 1.0
 ***************************************************************************/
public class HttpUtils {
//    static CloseableHttpClient closeableHttpClient = null;
//    static RequestConfig defaultRequestConfig = null;
    public static String get(String url) {

        String accesstoken = null;
        // 创建HttpClientBuilder
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
//        if (defaultRequestConfig == null) {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setStaleConnectionCheckEnabled(true).build();
//        }
//        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
//        if (closeableHttpClient == null) {
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .build();
//        }
        HttpGet httpGet = new HttpGet(url);

        try {
            // 执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            // 响应状态
            // 判断响应实体是否为空
            if (entity != null) {
                String entityString = EntityUtils.toString(entity);
//                System.err.println("请求地址:"+url +"\n"+"响应结果："+ httpResponse.getEntity()+" 数据："+entityString);
                return entityString;
            } else {
//                System.err.println("请求地址:"+url +"\n"+"响应结果失败");
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
//            try {
//                closeableHttpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }

    public static String getRequest(String url) {
        try {
            HttpGet httpRequest = new HttpGet(url);
            CloseableHttpClient httpclient = HttpClientUtils.getHttpClient();
            CloseableHttpResponse responseTemp = httpclient.execute(httpRequest);
            String responseResult = HttpClientUtils.getHttpEntiy(responseTemp);
            return responseResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
