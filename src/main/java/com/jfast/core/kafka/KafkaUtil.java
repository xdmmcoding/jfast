package com.jfast.core.kafka;
import com.jfast.core.kafka.send.KafkaSend;
import com.jfast.util.SpringUtils;

public class KafkaUtil {
	/**
	 * 
     * @Description kafka发送消息工具类  
     * @Author      xd  
     * @Date        2019年9月4日 下午5:07:07  
     * @param @param topic
     * @param @param parameter 参数  
     * @return void 返回类型   
     * @throws
	 */
	public static void send(String topic,String parameter){
		SpringUtils.getBean(KafkaSend.class).send(topic, parameter);
	}
}
