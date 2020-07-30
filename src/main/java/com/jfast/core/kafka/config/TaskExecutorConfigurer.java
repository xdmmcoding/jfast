package com.jfast.core.kafka.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@PropertySource(value = "classpath:kafka.properties",ignoreResourceNotFound = true)
public class TaskExecutorConfigurer {
	
    private static final Logger logger =LoggerFactory.getLogger(TaskExecutorConfigurer.class);
	@Autowired
    private Environment env;
	
	/**
	 * 
     * @Description 配置kafka线程池  
     * @Author      xd  
     * @Date        2019年9月5日 上午11:25:54  
     * @param @return 参数  
     * @return Executor 返回类型   
     * @throws
	 */
	@Bean("kafkaTaskExecutor")
	@ConditionalOnProperty(prefix = "kafka.task", name = "active", havingValue = "true")// 判断是否开启配置
	public Executor kafkaTaskExecutor() {
		logger.info("===========================[开启kafka线程池配置]================================");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();	
		//线程池创建时候初始化的线程数
		executor.setCorePoolSize(env.getProperty("kafka.task.corePoolSize",Integer.class));
		//线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
		executor.setMaxPoolSize(env.getProperty("kafka.task.maxPoolSize",Integer.class));
		//用来缓冲执行任务的队列
		executor.setQueueCapacity(env.getProperty("kafka.task.queueCapacity",Integer.class));
		//当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
		executor.setKeepAliveSeconds(env.getProperty("kafka.task.keepAliveSeconds",Integer.class));
		executor.setThreadNamePrefix("kafkaTaskExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，这样这些异步任务的销毁就会先于Redis线程池的销毁。同时，这里还设置了setAwaitTerminationSeconds
		executor.setWaitForTasksToCompleteOnShutdown(env.getProperty("kafka.task.waitForTasksToCompleteOnShutdown",Boolean.class));
		//该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
		executor.setAwaitTerminationSeconds(env.getProperty("kafka.task.awaitTerminationSeconds",Integer.class));
		//初始化
		executor.initialize();
		return executor;
	}
}
