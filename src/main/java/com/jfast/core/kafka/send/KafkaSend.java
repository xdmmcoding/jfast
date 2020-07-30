package com.jfast.core.kafka.send;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KafkaSend {
	
	@Autowired
    private KafkaTemplate<String, String> template;
	
	/**
	 * 
     * @Description 向kafka服务器发送数据  
     * @Author      xd  
     * @Date        2019年3月21日 下午3:35:40  
     * @param @param topic
     * @param @param parameter 参数  
     * @return void 返回类型   
     * @throws
	 */
    @Async("kafkaTaskExecutor")
	public void send(String topic,String parameter){
		this.template.send(topic, parameter);
	}
}
