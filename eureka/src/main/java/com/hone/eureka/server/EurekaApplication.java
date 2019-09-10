package com.hone.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>注册中心</p>
 * @author hourz
 * @since 2019-06-12
 */
@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
public class EurekaApplication {

	public static final Logger logger = LoggerFactory.getLogger(EurekaApplication.class);

	public static void main(String[] args) {
		logger.info("注册发现主服务 --> 启动！");
		SpringApplication.run(EurekaApplication.class, args);
	}
}
