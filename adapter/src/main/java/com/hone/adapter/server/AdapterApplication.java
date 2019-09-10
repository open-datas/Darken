package com.hone.adapter.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>交互调用中心</p>
 * @author hourz
 * @since 2019-06-10
 */
@SpringBootApplication
@EnableEurekaClient
public class AdapterApplication {

	public static final Logger logger = LoggerFactory.getLogger(AdapterApplication.class);

	public static void main(String[] args) {
		logger.info("调度中心主服务 --> 启动！");
		SpringApplication.run(AdapterApplication.class, args);
	}

}
