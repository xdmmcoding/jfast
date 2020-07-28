package com.jfast.core.datasource;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.jfast.util.AESUtil;

@Configuration
public class DataSourceConfig {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
    @Primary
    @Bean(name = "dataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnProperty(prefix = "spring.datasource.", name = "pwd-en-active", havingValue = "true", matchIfMissing = false)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConditionalOnProperty(prefix = "spring.datasource.", name = "pwd-en-active", havingValue = "true", matchIfMissing = false)
    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {
		String pwd = AESUtil.decryptBase64(dataSourceProperties.getPassword(), AESUtil.PASSWORD);
		if(StringUtils.isNotEmpty(pwd)){
			dataSourceProperties.setPassword(pwd);
		}else{			
			logger.error("数据库密码解密失败!");
		}
    	return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}