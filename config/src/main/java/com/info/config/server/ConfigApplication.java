package com.info.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>配置中心</p>
 * @author hourz
 * @since 2019-06-12
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigApplication {

	public static final Logger logger = LoggerFactory.getLogger(ConfigApplication.class);

	public static void main(String[] args) {
		logger.info("配置中心主服务 --> 启动！");
		SpringApplication.run(ConfigApplication.class, args);
	}
}
