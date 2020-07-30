package com.jfast.core.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import com.jfast.util.AESUtil;



@Configuration
@EnableKafka
@PropertySource(value = "classpath:kafka.properties",ignoreResourceNotFound = true)
public class ConsumerConfigurer {
	
    private static final Logger logger = LoggerFactory.getLogger(ConsumerConfigurer.class);
    @Autowired
    private Environment env;
	/**
	 * @throws Exception 
	 * 
     * @Description 配置消费者  
     * @Author      xd  
     * @Date        2019年9月4日 下午4:41:37  
     * @param @return 参数  
     * @return KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> 返回类型   
     * @throws
	 */
	@Bean
	@ConditionalOnProperty(prefix = "kafka.consumer", name = "active", havingValue = "true")// 判断是否开启配置
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() throws Exception {
		logger.info("=============================[开启kafka消费者配置]=====================================");
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(env.getProperty("kafka.consumer.concurrency",Integer.class));
		factory.getContainerProperties().setPollTimeout(1500);
		return factory;
	}
	//========================================================加载消费者配置=======================================================
	
	public ConsumerFactory<String, String> consumerFactory() throws Exception {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}
	
	public Map<String, Object> consumerConfigs() throws Exception {//开启权限认证
		boolean consumerAuthActive = env.getProperty("kafka.consumer.auth.active",Boolean.class);
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.consumer.servers"));
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, env.getProperty("kafka.consumer.enable.auto.commit",boolean.class));
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, env.getProperty("kafka.auto.commit.interval"));
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, env.getProperty("kafka.consumer.session.timeout"));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("kafka.consumer.group.id"));
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("kafka.consumer.auto.offset.reset"));
		//权限认证
		if(consumerAuthActive){			
			props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
			props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
			props.put("sasl.jaas.config",
					"org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+env.getProperty("kafka.consumer.auth.username")+"\" password=\""+AESUtil.decryptBase64(env.getProperty("kafka.consumer.auth.password"),AESUtil.PASSWORD)+"\";");
		}
		return props;
	}
}
