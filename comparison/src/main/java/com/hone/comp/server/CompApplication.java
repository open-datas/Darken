package com.hone.comp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>基础服务中心</p>
 * @author hourz
 * @since 2019-07-19
 */
@SpringBootApplication
@EnableEurekaClient
public class CompApplication {

	public static final Logger logger = LoggerFactory.getLogger(CompApplication.class);

	public static void main(String[] args) {
		logger.info("基础服务中心 ---> 启动！");
		SpringApplication.run(CompApplication.class, args);
	}

}
