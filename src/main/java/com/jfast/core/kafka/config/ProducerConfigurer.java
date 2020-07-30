package com.jfast.core.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.jfast.util.AESUtil;

@Configuration
@EnableKafka
@PropertySource(value = "classpath:kafka.properties",ignoreResourceNotFound = true)
public class ProducerConfigurer {
	
    private static final Logger logger = LoggerFactory.getLogger(ProducerConfigurer.class);
    @Autowired
    private Environment env;
	/**
	 * @throws Exception 
	 * 
     * @Description 配置生产者  
     * @Author      xd  
     * @Date        2019年9月4日 下午4:35:01  
     * @param @return 参数  
     * @return KafkaTemplate<String,String> 返回类型   
     * @throws
	 */
	@Bean
	@ConditionalOnProperty(prefix = "kafka.producer", name = "active", havingValue = "true")// 判断是否开启配置
	public KafkaTemplate<String, String> kafkaTemplate() throws Exception {
		logger.info("=============================[开启kafka生产者配置]=====================================");
		return new KafkaTemplate<String, String>(producerFactory());
	}
	//========================================================加载生产者配置=======================================================
	
	public Map<String, Object> producerConfigs() throws Exception {
		//开启权限认证
		boolean producerAuthActive = env.getProperty("kafka.producer.auth.active",Boolean.class);
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.producer.servers"));
		props.put(ProducerConfig.RETRIES_CONFIG, env.getProperty("kafka.producer.retries",Integer.class));
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, env.getProperty("kafka.producer.batch.size",Integer.class));
		props.put(ProducerConfig.LINGER_MS_CONFIG, env.getProperty("kafka.producer.linger",Integer.class));
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, env.getProperty("kafka.producer.buffer.memory",Integer.class));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		//权限认证
		if(producerAuthActive){			
			props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
			props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
			props.put("sasl.jaas.config",
					"org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+env.getProperty("kafka.producer.auth.username")+"\" password=\""+AESUtil.decryptBase64(env.getProperty("kafka.producer.auth.password"),AESUtil.PASSWORD)+"\";");
		}
		return props;
	}
	
	public ProducerFactory<String, String> producerFactory() throws Exception {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
}
