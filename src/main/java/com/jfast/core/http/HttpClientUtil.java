package com.jfast.core.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;


@Component("HttpClientUtil")
public class HttpClientUtil {
	
	@Autowired
    private CloseableHttpClient httpClient;
 
    @Autowired
    private RequestConfig config;
 
 
    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
 
        // 装载配置信息
        httpGet.setConfig(config);
 
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
 
    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, String> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
 
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, String> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
 
        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
 
    }
    
    public String doGet(String url, Map<String, String> map, Map<String, String> headers) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
 
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, String> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpGet httpGet = new HttpGet(url);
    
        if (headers != null && headers.size() > 0) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				httpGet.addHeader(key, headers.get(key));
			}
		}
        // 发起请求
        if(this.httpClient==null)
        	System.out.println("httpclient is null");
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        // 判断状态码是否为200
        return EntityUtils.toString(response.getEntity(), "UTF-8");
       
       
    }
 
    /**
     * 带参数的post请求
     * 
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public String doPost(String url, Map<String, String> map, Map<String, String> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.size() > 0) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				httpPost.addHeader(key, headers.get(key));
			}
		}
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
 
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
 
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
    
    public String doPost(String url, JSONObject obj, Map<String, String> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.size() > 0) {
			Set<String> keys = headers.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String key = (String) i.next();
				httpPost.addHeader(key, headers.get(key));
			}
		}
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        httpPost.setEntity(new StringEntity(obj.toString(), "utf-8"));
 
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
}
