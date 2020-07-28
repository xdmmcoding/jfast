package com.jfast.core.http.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClient {
 
   @Value("${httpClient.maxTotal}")
    private Integer maxTotal;
 
    @Value("${httpClient.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
 
    @Value("${httpClient.connectTimeout}")
    private Integer connectTimeout;
 
    @Value("${httpClient.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
 
    @Value("${httpClient.socketTimeout}")
    private Integer socketTimeout;
 
    @Value("${httpClient.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;
 
    /**
     * 
     * @Description 首先实例化一个连接池管理器，设置最大连接数、并发连接数  
     * @Author      lixudong  
     * @Date        2018年11月26日 下午2:25:52  
     * @param @return 参数  
     * @return PoolingHttpClientConnectionManager 返回类型   
     * @throws
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        //并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }
 
    /**
     * 
     * @Description 实例化连接池，设置连接池管理器。这里需要以参数形式注入上面实例化的连接池管理器  
     * @Author      lixudong  
     * @Date        2018年11月26日 下午2:26:02  
     * @param @param httpClientConnectionManager
     * @param @return 参数  
     * @return HttpClientBuilder 返回类型   
     * @throws
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager httpClientConnectionManager){
 
        //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
 
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
 
        return httpClientBuilder;
    }
 
    /**
     * 
     * @Description 注入连接池，用于获取httpClient  
     * @Author      lixudong  
     * @Date        2018年11月26日 下午2:26:20  
     * @param @param httpClientBuilder
     * @param @return 参数  
     * @return CloseableHttpClient 返回类型   
     * @throws
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }
 
    /**
     * 
     * @Description Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置  
     * @Author      lixudong  
     * @Date        2018年11月26日 下午2:26:34  
     * @param @return 参数  
     * @return RequestConfig.Builder 返回类型   
     * @throws
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout);
    }
 
    /**
     * 
     * @Description 使用builder构建一个RequestConfig对象  
     * @Author      lixudong  
     * @Date        2018年11月26日 下午2:26:55  
     * @param @param builder
     * @param @return 参数  
     * @return RequestConfig 返回类型   
     * @throws
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder){
        return builder.build();
    }
}
