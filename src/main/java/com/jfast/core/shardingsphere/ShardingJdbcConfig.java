package com.jfast.core.shardingsphere;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:sharding-jdbc.properties",ignoreResourceNotFound = true)
@AutoConfigureBefore(SpringBootConfiguration.class)
public class ShardingJdbcConfig {
	
}
