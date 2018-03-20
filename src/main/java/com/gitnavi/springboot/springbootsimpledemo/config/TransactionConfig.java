package com.gitnavi.springboot.springbootsimpledemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class TransactionConfig {

	@Bean
	public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("transactionInterceptor")
	public TransactionInterceptor transactionInterceptor(@Autowired DataSourceTransactionManager transactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(transactionManager);
		Properties transactionAttributes = new Properties();

		transactionAttributes.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
		transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
		transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
		transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
		transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
		transactionAttributes.setProperty("do*", "PROPAGATION_REQUIRED,-Exception");

		transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
		transactionAttributes.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
		transactionAttributes.setProperty("load*", "PROPAGATION_REQUIRED,readOnly");
		transactionAttributes.setProperty("query*", "PROPAGATION_REQUIRED,readOnly");
		transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,readOnly");

		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}

}
